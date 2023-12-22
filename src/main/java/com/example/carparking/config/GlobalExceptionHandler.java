package com.example.carparking.config;

import com.example.carparking.exception.ForbiddenEngineTypeException;
import com.example.carparking.exception.NotFoundException;
import com.example.carparking.exception.ParkingFullException;
import com.example.carparking.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ForbiddenEngineTypeException.class)
    public ResponseEntity<ApiError> handleForbiddenEngineTypeException(ForbiddenEngineTypeException e) {
        return new ResponseEntity<>(new ApiError(e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(new ApiError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParkingFullException.class)
    public ResponseEntity<ApiError> handleNotFoundException(ParkingFullException e) {
        return new ResponseEntity<>(new ApiError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getFieldErrors().stream()
                .map((error) ->
                        error.getField()
                                .concat(": ")
                                .concat(Optional.ofNullable(error.getDefaultMessage()).orElse("")))
                .toList();
        return new ResponseEntity<>(getErrorsMap(errors), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

}
