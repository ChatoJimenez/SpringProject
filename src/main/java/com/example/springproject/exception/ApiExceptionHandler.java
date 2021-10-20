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

    @ExceptionHandler(value = {BadRequest.class})
    public ResponseEntity<?> handleApiRequestException(BadRequest e) {
        Error badRequest = new Error(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        return new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(value = {Exception.class})
//    public ResponseEntity<?> handleGlobalException(Exception e) {
//        Error exception = new Error(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
//        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
//    }
}
