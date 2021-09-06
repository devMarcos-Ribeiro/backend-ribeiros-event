package com.marcosgrs.ribeirosevents.controllers.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SignInDto {

    @NotNull(message = "Username cannot be null.")
    @Length(max = 255, min = 3, message = "Username  must be between 3 and 255 characters.")
    private String email;

    @NotNull(message = "Password cannot be null.")
    @Length(max = 255, min = 8, message = "Password must be between 8 and 255 characters.")
    private String password;

    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}