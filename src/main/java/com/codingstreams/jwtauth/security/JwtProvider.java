package com.codingstreams.jwtauth.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    // Jwt Secret
    private final String jwtSecret = "sIoVC8OFOgmxbk9XRYtY2zMKXuYXBGL2d3x1IV37";

    // Jwt Expiration in millis
    private final Long jwtExpirationInMs = 60000L; // 1 min

    private Claims parseToken(String token) {
        try {
            // Create JwtParser
            JwtParser jwtParser = Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes())
                    .build();

            return jwtParser.parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
        } catch (UnsupportedJwtException e) {
        } catch (MalformedJwtException e) {
        } catch (SignatureException e) {
        } catch (IllegalArgumentException e) {
        }

        return null;
    }

    public boolean validateToken(String token) {
        return parseToken(token) != null;
    }

    public String getUsernameFromToken(String token) {
        // Get claims
        Claims claims = parseToken(token);

        // Extract subject
        if (claims != null) {
            return claims.getSubject();
        }

        return null;
    }

    public String generateToken(String username) {
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        // Create signing key
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        // Generate token
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();

        return token;
    }
}
