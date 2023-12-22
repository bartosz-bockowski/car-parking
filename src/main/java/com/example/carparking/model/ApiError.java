package com.example.carparking.model;

import lombok.Data;

@Data
public class ApiError {

    public ApiError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;

}
