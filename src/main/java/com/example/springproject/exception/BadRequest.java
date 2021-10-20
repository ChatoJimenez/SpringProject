package com.example.springproject.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class BadRequest extends RuntimeException{

    public BadRequest(String message) {
        super(message);
    }

    public BadRequest(String message, Throwable throwable){
        super(message, throwable);
    }

}
