package com.example.demo.Controller;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.demo.Model.UserModel;
import com.example.demo.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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
