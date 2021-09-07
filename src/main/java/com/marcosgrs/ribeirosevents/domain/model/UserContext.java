package com.marcosgrs.ribeirosevents.domain.model;

import com.marcosgrs.ribeirosevents.exceptions.UnauthorizedException;
import com.marcosgrs.ribeirosevents.service.TokenService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PreDestroy;

@Getter
public class UserContext {

    private final TokenService tokenService;

    @Getter
    @Setter
    private String userId;

    public UserContext(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public void update(final String authorization) {
        clear();

        if (StringUtils.isNotBlank(authorization)) {
            try {
                final String token = this.tokenService.retrieveToken(String.format("Bearer %s", authorization));
                this.userId = this.tokenService.getUserId(token);
            } catch (RuntimeException e) {
                throw new UnauthorizedException(e.getMessage());
            }
        }
    }

    @PreDestroy
    public void clear() {
        this.userId = null;
    }
}
