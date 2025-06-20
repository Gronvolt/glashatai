package com.neomodeon.glashatai.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtProperties jwtProperties;

    private Key getSigninKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    public boolean validateToken(String token) {
            try {
                extractClaims(token);
                return !isTokenExpired(token);
            } catch (JwtException | IllegalArgumentException e) {
                return false;
            }
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
