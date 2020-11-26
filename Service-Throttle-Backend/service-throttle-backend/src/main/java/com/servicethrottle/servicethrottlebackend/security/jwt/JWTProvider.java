package com.servicethrottle.servicethrottlebackend.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.var;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JWTProvider {
    private final String SECRET_KEY = "secretSecretSecretSecretSecretSecret";
    private final int jwtExpirationInMillis = 1000 * 60 * 60 * 10;
//    private Key key = ;

    //    jwt generation using authentication
    public String generateToken(Authentication authentication) throws Exception {
        User principal = (User) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", principal.getAuthorities());
        System.out.println(principal.getUsername());
        return Jwts.builder()
//                .setClaims(claims)
                .setSubject(principal.getUsername())
                .claim("authorities", principal.getAuthorities())
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        var authorities = (List<Map<String, String>>) claims.get("authorities");

        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                .collect(Collectors.toSet());

//        User principal = new User(claims.getSubject(), "", simpleGrantedAuthorities);

//        System.out.println(principal.getAuthorities());
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), "", simpleGrantedAuthorities);
    }

    public boolean validateToken(String jwt) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwt);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            new RuntimeException(e);
        }
        return false;
    }
}
