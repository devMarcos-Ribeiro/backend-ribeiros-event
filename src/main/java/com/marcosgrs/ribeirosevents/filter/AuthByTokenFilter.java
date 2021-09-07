package com.marcosgrs.ribeirosevents.filter;

import com.marcosgrs.ribeirosevents.domain.entity.User;
import com.marcosgrs.ribeirosevents.domain.model.UserContext;
import com.marcosgrs.ribeirosevents.domain.repository.UserRepository;
import com.marcosgrs.ribeirosevents.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthByTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository repository;
    private final UserContext userContext;

    public AuthByTokenFilter(TokenService tokenService, UserRepository repository, UserContext userContext) {
        this.tokenService = tokenService;
        this.repository = repository;
        this.userContext = userContext;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = tokenService.retrieveToken(request.getHeader("Authorization"));
        if (tokenService.isValidToken(token)) {
            userContext.update(token);
            authUser(token);
        }

        filterChain.doFilter(request, response);
    }

    private void authUser(String token) {
        String userId = tokenService.getUserId(token);
        User user = repository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found.", userId)));
        UsernamePasswordAuthenticationToken authentication = new
                UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
