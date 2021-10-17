package com.example.springproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {InternalServerError.class})
    public ResponseEntity<?> handleInternalServerError(InternalServerError e) {
        Error resourceNotFound = new Error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now());
        return new ResponseEntity<>(resourceNotFound, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<?> handleApiRequestException(ApiRequestException e) {
        Error apiException = new Error(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleGlobalException(Exception e) {
        Error exception = new Error(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}
