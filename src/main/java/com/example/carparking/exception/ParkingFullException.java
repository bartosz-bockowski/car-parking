package com.example.carparking.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ParkingFullException extends RuntimeException {
    public ParkingFullException(String message) {
        super(message);
    }
}
