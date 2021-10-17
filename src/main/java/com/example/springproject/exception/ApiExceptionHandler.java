package com.example.springproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        Error resourceNotFound = new Error(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(resourceNotFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<?> handleApiRequestException(ApiRequestException e) {
        Error apiException = new Error(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleGlobalException(Exception e) {
        //1. Create a payload
        Error exception = new Error(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        //2. Return response entity
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}
