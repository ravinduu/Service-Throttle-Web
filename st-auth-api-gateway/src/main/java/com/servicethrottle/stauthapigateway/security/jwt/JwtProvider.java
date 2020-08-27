package com.servicethrottle.stauthapigateway.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtProvider {

    private final String SECRET_KEY = "secret";
    private final int jwtExpirationInMillis = 1000 * 60 * 60 * 10;
    private Key key;
    private long tokenValidityInMilliseconds;


    @PostConstruct
    public void init() {
        byte[] keyBytes;
//        String secret = SECRET_KEY;
//        if (!StringUtils.isEmpty(secret)) {
//            keyBytes = secret.getBytes(StandardCharsets.UTF_8);
//        } else {
//            keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        }
        keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.tokenValidityInMilliseconds =1000 * 60 * 60 * 10;
    }

//    jwt generation using authentication
    public String generateToken(Authentication authentication) throws Exception {
        User principal = (User) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        System.out.println(principal.getUsername());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(principal.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(SignatureAlgorithm.HS256, key)
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

//    jwt generation using username
    public String generateTokenWithUserName(String username) throws Exception {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(SignatureAlgorithm.HS256, key)
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

    public String getAuthentication(String jwt){
        if (validateToken(jwt)){
            Claims claims = Jwts
                    .parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt)
                    .getBody();

            return claims.getSubject();
        }
        return null;
    }

    public boolean validateToken(String jwt) {
        try {
            Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
//            throw new RuntimeException(e);
        }
        return false;
    }
}
