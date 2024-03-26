package com.example.demo.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.demo.Model.UserModel;
import com.example.demo.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    Jackson2ObjectMapperBuilder mapperBuilder;
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping(value = "/dashboard")
    public String handleIndex(){
        return "dashboard";
    }
    @GetMapping(value = {"/","/login"})
    public String handleLogin(HttpServletRequest request){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if(authentication instanceof AnonymousAuthenticationToken){
        return "login";
    }else{
        if (authentication.getAuthorities().toString().contains("ROLE_ADMIN")){
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/admin";
        }else {
            return "redirect:/dashboard";
        }
    }
    }
    @GetMapping(value = "/login?error")
    public String handleLoginError(){
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
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestParam("name") String name,
                                         @RequestParam("username") String username,
                                         @RequestParam("password") String password) throws JsonProcessingException {

        UserModel existingUser = myUserDetailsService.uname(username);
        String toJson = "";
        ObjectMapper mapper = mapperBuilder.build();
        Map<String, Object> callBackData = new HashMap<>();
        System.out.println(existingUser);
        if (existingUser.getUsername() == null) {
            existingUser.setName(name);
            String encodedPassword = passwordEncoder.encode(password);
            existingUser.setPassword(encodedPassword);
            existingUser.setUsername(username);
            existingUser.setRole("USER");
            myUserDetailsService.save(existingUser);
            callBackData.put("result", true);
            callBackData.put("message", "Account Created");
        } else {
            callBackData.put("result", false);
            callBackData.put("message", "Username already exists");
        }
        toJson = mapper.writeValueAsString(callBackData);
        return ResponseEntity.status(HttpStatus.OK).body(toJson);
    }
}
