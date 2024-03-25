package com.example.demo.Service;

import com.example.demo.Model.UserModel;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    public UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(userObj.getRole())
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public UserModel save(UserModel user){
        return userRepository.save(user);
    }

    public String uname(String username){
        String checker = userRepository.findUsernameIfExists1(username);
        if (checker==null){
            return null;
        }else {
            return "exist";
        }

    }
}
