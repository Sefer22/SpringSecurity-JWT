package com.example.securityjwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private String SECRET_KEY = "jwttutorial";

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).build().parseSignedClaims(token).getBody();
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username) {
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,username);
    }


}
