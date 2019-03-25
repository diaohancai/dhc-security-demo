package com.hancai.demo.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-security")
public class HelloSecurity {

    @RequestMapping("/hello")
    public String hello() {
        return "hello spring security";
    }

}
