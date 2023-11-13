package com.example.library_management_platform.config;

import com.example.library_management_platform.interceptors.AdminAuthInterceptor;
import com.example.library_management_platform.interceptors.BorrowerAuthInterceptor;
import com.example.library_management_platform.interceptors.LibraryManagerAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LibraryManagerAuthInterceptor libraryManagerAuthInterceptor;

    @Autowired
    private BorrowerAuthInterceptor borrowerAuthInterceptor;

    @Autowired
    private AdminAuthInterceptor adminAuthInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // APIs that can be accessed using Borrower authentication token
        registry.addInterceptor(borrowerAuthInterceptor).addPathPatterns(
                "/api/borrowers/*",
                "/api/issuance"
        ).excludePathPatterns("/api/borrowers/login").order(Ordered.HIGHEST_PRECEDENCE);

        // APIs that can be accessed using Library Manager authentication token
        registry.addInterceptor(libraryManagerAuthInterceptor).addPathPatterns(
                "/api/books",
                "/api/genres",
                "/api/books/delete/*",
                "/api/genres/delete/*",
                "/api/genres/update/*",
                "/api/issuance/update",
                "/api/issuance/all",
                "/api/library/managers/*",
                "/api/library/membership",
                "/api/library/membership/*"
        ).excludePathPatterns("/api/library/managers/login").order(Ordered.HIGHEST_PRECEDENCE);

        // APIs that can be accessed using admin secret key of the application.
        registry.addInterceptor(adminAuthInterceptor).addPathPatterns(
                "/api/library/managers"
        ).order(Ordered.HIGHEST_PRECEDENCE);
    }

}
