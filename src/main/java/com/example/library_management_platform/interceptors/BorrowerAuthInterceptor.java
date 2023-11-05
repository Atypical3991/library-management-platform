package com.example.library_management_platform.interceptors;

import com.example.library_management_platform.constants.HeaderParams;
import com.example.library_management_platform.utils.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Slf4j
public class BorrowerAuthInterceptor implements HandlerInterceptor {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationToken = request.getHeader(HeaderParams.AUTHORIZATION);
        if(authorizationToken ==  null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid or missing oAuthorization header.");
        }
        String username  = jwtTokenUtil.parseJwt(authorizationToken);
        if(username == null){
            log.error("BorrowerAuthInterceptor, preHandle invalid token!!");
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
