package com.example.shopping.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenUtils {
    private final Key secretKey;
    private final JwtParser jwtParser;

    public JwtTokenUtils(
            @Value("${jwt.secret}")
            String jwtSecret
    ){
        log.info("jwtSecret: {}", jwtSecret);
        this.secretKey =
                Keys.hmacShaKeyFor(jwtSecret.getBytes());
        this.jwtParser = Jwts
                .parserBuilder()
                .setSigningKey(this.secretKey)
                .build();
    }
    public String generateToken (UserDetails userDetails){
        Instant now = Instant.now();
        Claims jwtClaims = Jwts.claims()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(60*60)));

        return  Jwts.builder()
                .setClaims(jwtClaims)
                .signWith(this.secretKey)
                .compact();
    }
    public boolean validate (String token){
        try {
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            log.info("subject: {}", claims.getSubject());
            log.info("issueAt : {}", claims.getIssuedAt());
            log.info("expireAt: {}", claims.getExpiration());
            return true;
        } catch (Exception e) {
            log.warn("invalid jwt provided : {}", e.getMessage());
        }
        return false;
    }
    public Claims parseClaims(String token){
        return jwtParser.parseClaimsJws(token).getBody();
    }

}
