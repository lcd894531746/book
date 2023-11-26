package com.dong.book.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/account"})
public class AccontController {
    @RequestMapping(value = "/toRegister",method = RequestMethod.POST)
    public String toRegister(){
        System.out.println("------toRegister");
        return "register";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "index";
    }
}
