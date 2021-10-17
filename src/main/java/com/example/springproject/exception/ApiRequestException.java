package com.example.springproject.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ApiRequestException extends RuntimeException{

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable throwable){
        super(message, throwable);
    }

}
