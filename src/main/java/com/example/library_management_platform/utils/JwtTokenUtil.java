package com.example.library_management_platform.utils;

import com.example.library_management_platform.component.EnvConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.Map;

import static javax.crypto.Cipher.SECRET_KEY;


@Slf4j
public class JwtTokenUtil {

    private static Key getSigningKey(){
        return new SecretKeySpec(System.getenv("SECRET_KEY").getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }


    public static String generateJwt(Map<String,Object> claims) {
        Key SIGNING_KEY = getSigningKey();

        Date now = new Date();
        Date expiration = new Date(now.getTime() + System.getenv("EXPIRATION_TIME"));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    public static Claims parseJwt(String token) {
        Key SIGNING_KEY = getSigningKey();
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SIGNING_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (Exception e) {
            log.error("JwtTokenUtil, exception raised!!",e);
            return null; // Token is invalid or expired
        }
    }
}
