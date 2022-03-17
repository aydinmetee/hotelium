package com.hotelium.mainservice.util;

import com.hotelium.mainservice.dto.auth.TokenResponseDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Service
public class JwtUtil {
    private String SECRET_KEY = "metea";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public TokenResponseDTO generateToken(String username, String id, String orgId) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username, id, orgId);
    }

    private TokenResponseDTO createToken(Map<String, Object> claims, String subject, String id, String orgId) {
        final var token = Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        return TokenResponseDTO.builder()
                .accessToken(token)
                .userName(subject)
                .userId(id)
                .orgId(orgId)
                .expiredTime(new Date(System.currentTimeMillis() + 5 * 60 * 60 * 1000))
                .build();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}