package com.example.library_management_platform.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.Map;


@Slf4j
public class JwtTokenUtil {

    private static Key getSigningKey() {
        return new SecretKeySpec(System.getenv("SECRET_KEY").getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    //generateJwt :- A method to generate Jwt tokens
    public static String generateJwt(Map<String, Object> claims) {
        Key SIGNING_KEY = getSigningKey();

        Date now = new Date();
        Date expiration = new Date(now.getTime() + Long.parseLong(System.getenv("EXPIRATION_TIME")));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    //parseJwt :- A method to parse Jwt tokens and extract claims
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
            // Token is invalid or expired
            log.error("JwtTokenUtil, exception raised!!", e);
            return null;
        }
    }
}
