package com.example.library_management_platform.interceptors;

import com.example.library_management_platform.constants.HeaderParams;
import com.example.library_management_platform.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class AdminAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationToken = request.getHeader(HeaderParams.ADMIN_SECRET_KEY_HEADER);
        if(authorizationToken ==  null || authorizationToken.equalsIgnoreCase(System.getenv("ADMIN_SECRET_KEY"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid or missing oAuthorization header.");
        }
        return true;
    }
}
