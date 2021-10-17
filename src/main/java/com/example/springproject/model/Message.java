package com.example.springproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class Message {

    private final String message;
    private final int statusValue;
    private final HttpStatus status;
    private final ZonedDateTime timestamp;

}
