package com.marcosgrs.ribeirosevents.controllers.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class SignUpDto {

    @NotNull(message = "Username cannot be null.")
    @Length(max = 255, min = 3, message = "Username  must be between 3 and 255 characters.")
    private String email;

    @NotNull(message = "Name cannot be null.")
    @Length(max = 255, min = 3, message = "Name  must be between 3 and 255 characters.")
    private String name;

    @NotNull(message = "Password cannot be null.")
    @Length(max = 255, min = 8, message = "Password must be between 8 and 255 characters.")
    private String password;

    @NotNull(message = "Password cannot be null.")
    @Length(max = 255, min = 8, message = "Password confirmation also must be between 8 and 255 characters.")
    private String passwordConfirmation;
}
