package com.authentication.authentication.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RequestMapping("/")
public class HomeControllers {
    @GetMapping("/")
    public String home() {
        return "forward:/index.html";
    }
    @GetMapping("/signup")
    public String signup() {
        return "forward:/signup.html";
    }

    @GetMapping("/login")
    public String login() {
        return "forward:/login.html";
    }

    @GetMapping("/verify")
    public String verify() {
        return "forward:/verify.html";
    }
}
