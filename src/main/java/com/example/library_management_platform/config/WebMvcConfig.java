package com.example.library_management_platform.config;

import com.example.library_management_platform.interceptors.AdminAuthInterceptor;
import com.example.library_management_platform.interceptors.BorrowerAuthInterceptor;
import com.example.library_management_platform.interceptors.LibraryManagerAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // Borrower authentication interceptor
        registry.addInterceptor(new BorrowerAuthInterceptor()).addPathPatterns("/api/books/all", "/api/genres/all", "/api/borrowers/*", "/api/issuance");
        registry.addInterceptor(new LibraryManagerAuthInterceptor()).addPathPatterns(
                "/api/books", "/api/genres", "/api/books/delete/*", "/api/genres/delete/*", "/api/genres/update/*", "/api/issuance/update",
                "/api/issuance/active/all", "/api/borrowers", "/api/library/managers/*"
        );
        registry.addInterceptor(new AdminAuthInterceptor()).addPathPatterns(
                "/api/library/managers"
        );
    }

}
