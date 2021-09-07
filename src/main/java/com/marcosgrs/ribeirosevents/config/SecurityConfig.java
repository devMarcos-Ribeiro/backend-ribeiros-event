package com.marcosgrs.ribeirosevents.config;

import com.marcosgrs.ribeirosevents.domain.model.UserContext;
import com.marcosgrs.ribeirosevents.filter.AuthByTokenFilter;
import com.marcosgrs.ribeirosevents.domain.repository.UserRepository;
import com.marcosgrs.ribeirosevents.service.RibeirosEventsUserDetailsService;
import com.marcosgrs.ribeirosevents.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final RibeirosEventsUserDetailsService ribeirosEventsUserDetailsService;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final UserContext userContext;

    @Autowired
    public SecurityConfig(RibeirosEventsUserDetailsService ribeirosEventsUserDetailsService,
                          TokenService tokenService, UserRepository userRepository, UserContext userContext) {
        this.ribeirosEventsUserDetailsService = ribeirosEventsUserDetailsService;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.userContext = userContext;
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(ribeirosEventsUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/signUp").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AuthByTokenFilter(tokenService, userRepository, userContext),
                UsernamePasswordAuthenticationFilter.class);
    }
}
