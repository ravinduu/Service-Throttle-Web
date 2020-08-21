package com.servicethrottle.stauthapigateway.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtProvider {

    private final String SECRET_KEY = "secret";
    private final int jwtExpirationInMillis = 1000 * 60 * 60 * 10;

//    jwt generation using authentication
    public String generateToken(Authentication authentication) throws Exception {
        User principal = (User) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        System.out.println(principal.getUsername());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(principal.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

//    jwt generation using username
    public String generateTokenWithUserName(String username) throws Exception {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }
}
