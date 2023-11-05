package com.example.library_management_platform.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvConfig {

    @Value("${jwt.secret.key}")
    public String SECRET_KEY;

    @Value("${jwt.expiration.time}")
    public long EXPIRATION_TIME;


}
