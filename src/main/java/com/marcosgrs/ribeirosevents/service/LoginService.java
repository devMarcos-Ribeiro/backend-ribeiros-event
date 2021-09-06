package com.marcosgrs.ribeirosevents.service;

import com.marcosgrs.ribeirosevents.controllers.dto.SignInDto;
import com.marcosgrs.ribeirosevents.controllers.dto.TokenResponse;
import com.marcosgrs.ribeirosevents.exceptions.AuthenticationException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public TokenResponse authenticate(SignInDto loginDto) {
        UsernamePasswordAuthenticationToken loginData = loginDto.convert();
        try {
            Authentication authentication = authenticationManager.authenticate(loginData);
            String token = tokenService.generateToken(authentication);
            return new TokenResponse(token, "Bearer");
        } catch (Exception e) {
            throw new AuthenticationException(String.format("Error authenticating user %s: %s", loginDto.getEmail(), e.getMessage()));
        }
    }
}
