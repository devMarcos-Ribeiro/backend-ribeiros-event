package com.marcosgrs.ribeirosevents.exceptions;

public class UserAlreadyExistsException extends BadRequestException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
