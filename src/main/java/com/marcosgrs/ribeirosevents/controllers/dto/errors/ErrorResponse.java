package com.marcosgrs.ribeirosevents.controllers.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    private String message;
    private OffsetDateTime timestamp;
    private HttpStatus status;

}
