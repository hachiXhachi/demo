package com.example.demo.Controller;

import com.example.demo.Model.UserModel;
import com.example.demo.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
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
    @GetMapping(value = "/signup")
    public String handlesignup(){
        return "sign_up";
    }
    @PostMapping(value = "/create")
    public String create(@RequestParam("name") String name,
                         @RequestParam("username") String username,
                         @RequestParam("password")String password) {
        String checker = myUserDetailsService.uname(username);
        if (checker==null){
            UserModel userModel = new UserModel();
            userModel.setName(name);
            String passEncoder = passwordEncoder.encode(password);
            userModel.setPassword(passEncoder);
            userModel.setUsername(username);
            userModel.setRole("USER");
            myUserDetailsService.save(userModel);
        }
        return "login";
    }
}
