package com.marcosgrs.ribeirosevents.exceptions;

public class InvalidSignUpDataException extends BadRequestException {

    public InvalidSignUpDataException(String message) {
        super(message);
    }
}
