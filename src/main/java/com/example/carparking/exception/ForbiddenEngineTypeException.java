package com.example.carparking.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ForbiddenEngineTypeException extends RuntimeException {

    public ForbiddenEngineTypeException(String message) {
        super(message);
    }

}
