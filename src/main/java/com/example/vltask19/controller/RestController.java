package com.example.vltask19.controller;


import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @RequestMapping("/")
    public String Hello() {
        return "欢迎回来";
    }
}
