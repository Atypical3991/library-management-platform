package com.example.library_management_platform.config;

import com.example.library_management_platform.interceptors.BorrowerAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BorrowerAuthInterceptor()).addPathPatterns("/api/books/**");
    }

}
