package com.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/")
public interface HelloRest {

    @GetMapping(path = "/")
    String hello();
} 
