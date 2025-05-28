package com.demo.restlmpl;

import org.springframework.web.bind.annotation.RestController;

import com.demo.rest.HelloRest;

@RestController
public class HelloRestImpl implements HelloRest{
    
    @Override
    public String hello() {
        return "✅ Spring Boot is running on Render, connected to Railway DB!";
    }
}
