package com.okta.developer.jugtours.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mypage")
public class testContorller {


    @GetMapping("/message")
    public String mypage()
    {
        return "is mypage !!";
    }
}

