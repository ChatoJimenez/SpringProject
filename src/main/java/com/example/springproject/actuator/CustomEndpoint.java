package com.example.springproject.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="custom-endpoint")
public class CustomEndpoint {

    @ReadOperation
    public String customEndpoint(){
        return "Hello from custom endpoint";
    }

}
