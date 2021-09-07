package com.marcosgrs.ribeirosevents.controllers.dto;

import lombok.Data;

@Data
public class DefaultResponse {

    private String message;

    public DefaultResponse(String message) {
        this.message = message;
    }
}
