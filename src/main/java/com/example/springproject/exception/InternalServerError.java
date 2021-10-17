package com.example.springproject.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class InternalServerError extends RuntimeException{

    public InternalServerError(String message) {
        super(message);
    }

    public InternalServerError(String message, Throwable throwable){
        super(message, throwable);
    }

}
