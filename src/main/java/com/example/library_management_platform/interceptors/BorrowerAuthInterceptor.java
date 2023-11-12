package com.example.library_management_platform.interceptors;

import com.example.library_management_platform.constants.HeaderParams;
import com.example.library_management_platform.models.entities.Sessions;
import com.example.library_management_platform.models.entities.User;
import com.example.library_management_platform.repositories.SessionsRepository;
import com.example.library_management_platform.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class BorrowerAuthInterceptor implements HandlerInterceptor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationToken = request.getHeader(HeaderParams.AUTHORIZATION);
        if (authorizationToken == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing auth token.");
        }

        // Checking whether an active Sessions exist with the passed token or not.
        Sessions session = this.applicationContext.getBean(SessionsRepository.class).findTopByToken(authorizationToken);
        if (session == null) {
            // Throwing exception in case of no active Sessions.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token not active.");
        }

        Claims claims = JwtTokenUtil.parseJwt(authorizationToken);
        if (claims == null) {
            // Throwing exception on Invalid or Expired token
            // Also deleting Sessions if present any
            this.applicationContext.getBean(SessionsRepository.class).deleteSessionsByToken(authorizationToken);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token expired.");
        }

        String username = (String) claims.get("username");
        String role = (String) claims.get("role");

        // Role inside Claims must `borrower` type.
        if (username == null || role == null || !role.equals(User.RoleEnum.borrower.name())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token not acceptable.");
        }
        request.setAttribute("username", username);
        return true;
    }

}
