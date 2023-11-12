package com.example.library_management_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.library_management_platform.repositories")
public class LibraryManagementPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementPlatformApplication.class, args);
    }

}
