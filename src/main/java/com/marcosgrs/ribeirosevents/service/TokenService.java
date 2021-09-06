package com.marcosgrs.ribeirosevents.service;

import com.marcosgrs.ribeirosevents.config.RibeirosEventsProperties;
import com.marcosgrs.ribeirosevents.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class TokenService {

    private final RibeirosEventsProperties ribeirosEventsProperties;

    @Autowired
    public TokenService(RibeirosEventsProperties ribeirosEventsProperties) {
        this.ribeirosEventsProperties = ribeirosEventsProperties;
    }

    public String generateToken(Authentication authentication) {
        String jwt = null;
        User logged = (User) authentication.getPrincipal();
        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(ribeirosEventsProperties.getJwtExpiration()));
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setIssuer("Ribeiros Events backend")
                .setSubject(logged.getId())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, ribeirosEventsProperties.getJwtSecret()).compact();

    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(ribeirosEventsProperties.getJwtSecret()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(ribeirosEventsProperties.getJwtSecret()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String retrieveToken(String authorization) {
        if (authorization == null || authorization.isEmpty() || !authorization.startsWith("Bearer ")) {
            return null;
        }
        return authorization.substring(7);
    }
}