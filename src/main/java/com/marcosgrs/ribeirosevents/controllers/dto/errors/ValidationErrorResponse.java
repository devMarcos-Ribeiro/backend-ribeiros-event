package com.marcosgrs.ribeirosevents.controllers.dto.errors;

import lombok.Data;

@Data
public class ValidationErrorResponse {

    private String field;
    private String details;

    public ValidationErrorResponse(String field, String details) {
        this.field = field;
        this.details = details;
    }
}