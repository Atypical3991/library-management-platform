package com.example.library_management_platform.interceptors;

import com.example.library_management_platform.constants.HeaderParams;
import com.example.library_management_platform.models.entities.User;
import com.example.library_management_platform.repositories.SessionsRepository;
import com.example.library_management_platform.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Slf4j
@Component
public class LibraryManagerAuthInterceptor implements HandlerInterceptor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationToken = request.getHeader(HeaderParams.AUTHORIZATION);
        if(authorizationToken ==  null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing auth token.");
        }
        Claims claims  = JwtTokenUtil.parseJwt(authorizationToken);
        if(claims == null){
            this.applicationContext.getBean(SessionsRepository.class).deleteByToken(authorizationToken);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid auth token.");
        }
        String username = (String) claims.get("username");
        User.RoleEnum role = (User.RoleEnum) claims.get("role");
        if(username == null || role != User.RoleEnum.library_manager){
            log.error("LibraryManagerAuthInterceptor, preHandle invalid token!!");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid token!");
            response.getWriter().flush();
            response.getWriter().close();
            return false;
        }
        request.setAttribute("username",username);
        return true;
    }

}
