package com.example.library_management_platform.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvConfig {

    // JWT secret key used in generating jwt token
    @Value("${jwt.secret.key}")
    public String SECRET_KEY;

    // JWT expiration time, to add expiration in generated JWT token
    @Value("${jwt.expiration.time}")
    public long EXPIRATION_TIME;
}
