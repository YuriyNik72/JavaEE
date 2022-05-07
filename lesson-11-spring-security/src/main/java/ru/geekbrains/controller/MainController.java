package ru.geekbrains.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/")
@Controller
public class MainController {

    @GetMapping
    public String mainPage(){
        return "main";
    }

    @GetMapping("/access_denied")
    public String accessDenied(){
        return "access_denied";
    }

}
