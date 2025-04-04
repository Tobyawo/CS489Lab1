package edu.miu.cs.cs489appsd.lab1a.productmgmtapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping(value = {"/", "/home", "/elibrary"})
    public String home() {
        return "home/index";
    }
}
