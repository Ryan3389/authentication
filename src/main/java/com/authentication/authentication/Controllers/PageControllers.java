package com.authentication.authentication.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageControllers {

    @GetMapping("/")
    public String index() {
        return "forward:index.html";
    }

    @GetMapping("/signup")
    public String about() {
        return "forward:signup.html";
    }
    @GetMapping("/login")
    public String login() {
        return "forward:login.html";
    }

    @GetMapping("/verify")
    public String verify() {
        return "forward:verify.html";
    }
}
