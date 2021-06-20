package com.l.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;

//自动装配：原理！
@RestController
public class HelloController {

    //接口 http://localhost:8080/hello
    @RequestMapping("/hello")
    public String hello() {
        return "hello,world";

    }
}
