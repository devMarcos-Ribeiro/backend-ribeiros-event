package com.marcosgrs.ribeirosevents.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponse {

    private final String token;
    private final String type;

}
