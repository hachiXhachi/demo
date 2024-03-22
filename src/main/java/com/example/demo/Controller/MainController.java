package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @GetMapping(value = "/home")
    public String handleWelcome(){
        return "home";
    }
    @GetMapping(value = "/dashboard")
    public String handleIndex(){
        return "dashboard";
    }
    @GetMapping(value = "/login")
    public String handleLogin(){
        return "login";
    }

    @GetMapping(value = "/admin")
    public String handleadmin(){
        return "admin_dashboard";
    }
    @PostMapping(value = "/logout")
    public String handlelogout(){
        return "logout";
    }

}
