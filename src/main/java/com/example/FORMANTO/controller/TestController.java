package com.example.FORMANTO.controller;

import com.example.FORMANTO.domain.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test/test";
    }

    @PostMapping("/test")
    public String test2(){
        return "redirect:/test";
    }

    public String test22(){

        List<Cart> carts = new ArrayList<>();

        List<Long> cartIds = carts.stream().mapToLong(cart -> cart.getCartId()).boxed().toList();



        return null;
    }
}
