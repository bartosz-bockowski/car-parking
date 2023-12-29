package com.example.carparking.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

}
