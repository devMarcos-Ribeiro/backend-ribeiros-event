package com.marcosgrs.ribeirosevents.mapper;

import com.marcosgrs.ribeirosevents.controllers.dto.SignUpDto;
import com.marcosgrs.ribeirosevents.domain.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SignUpMapper {

    public static User toEntity(SignUpDto signUpDto) {
        return User.builder()
                .email(signUpDto.getEmail())
                .password(new BCryptPasswordEncoder().encode(signUpDto.getPassword()))
                .name(signUpDto.getName())
                .build();
    }
}
