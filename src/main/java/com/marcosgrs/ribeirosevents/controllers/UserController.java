package com.marcosgrs.ribeirosevents.controllers;

import com.marcosgrs.ribeirosevents.controllers.dto.SignInDto;
import com.marcosgrs.ribeirosevents.controllers.dto.SignUpDto;
import com.marcosgrs.ribeirosevents.controllers.dto.TokenResponse;
import com.marcosgrs.ribeirosevents.service.LoginService;
import com.marcosgrs.ribeirosevents.service.RibeirosEventsUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final LoginService loginService;
    private final RibeirosEventsUserDetailsService ribeirosEventsUserDetailsService;

    @Autowired
    public UserController(LoginService loginService,
                          RibeirosEventsUserDetailsService ribeirosEventsUserDetailsService) {
        this.loginService = loginService;
        this.ribeirosEventsUserDetailsService = ribeirosEventsUserDetailsService;
    }

    @PostMapping("/auth")
    public ResponseEntity<TokenResponse> authUser(@RequestBody SignInDto signInDto) {
        return ResponseEntity.ok(loginService.authenticate(signInDto));
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUpUser(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(ribeirosEventsUserDetailsService.signup(signUpDto));
    }
}
