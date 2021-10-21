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
        Error internalServerError = new Error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now());
        return new ResponseEntity<>(internalServerError, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = {NumberFormatException.class})
    public ResponseEntity<?> handleNumberFormatException(NumberFormatException e) {
        Error badRequest = new Error("The ID provided is not an integer", HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        return new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
    }
}
