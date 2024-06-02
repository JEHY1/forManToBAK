package com.example.FORMANTO.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressTestController {

    @GetMapping("/addressTest")
    public String addressTest(){
        return "addressTest/addressTest";
    }
}
