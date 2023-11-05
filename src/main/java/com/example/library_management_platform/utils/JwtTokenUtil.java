package com.example.library_management_platform.utils;

import com.example.library_management_platform.component.EnvConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

import static javax.crypto.Cipher.SECRET_KEY;


@Component
@Slf4j
public class JwtTokenUtil {

    @Autowired
    EnvConfig envConfig;


    private Key getSigningKey(){
        return new SecretKeySpec(envConfig.SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }
    public String generateJwt(String subject) {
        Key SIGNING_KEY = getSigningKey();

        Date now = new Date();
        Date expiration = new Date(now.getTime() + envConfig.EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    public  String parseJwt(String token) {
        Key SIGNING_KEY = getSigningKey();
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SIGNING_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            log.error("JwtTokenUtil, exception raised!!",e);
            return null; // Token is invalid or expired
        }
    }
}
