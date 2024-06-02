package com.example.FORMANTO.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class JoinViewController {

    @GetMapping("/login")
    public String login(){
        return "join/login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "join/signup";
    }

}
