package com.marcosgrs.ribeirosevents.config;

import com.marcosgrs.ribeirosevents.domain.model.UserContext;
import com.marcosgrs.ribeirosevents.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfig {

    private final TokenService tokenService;

    @Autowired
    public ContextConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Bean
    public UserContext retrievesUserContext(TokenService tokenService) {
        return new UserContext(tokenService);
    }
}
