package com.gratjar.notes.controller;

import com.gratjar.notes.entity.User;
import com.gratjar.notes.model.UserDTO;
import com.gratjar.notes.repository.UserRepository;
import com.gratjar.notes.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByUsername(username);

        if (user.getUsername().equals(username) && passwordEncoder.matches(password, user.getPassword())) {
            return jwtUtil.generateToken(username);
        }
        throw new RuntimeException("Invalid credentials");
    }

@PostMapping("/signup")
    public String signup(@RequestBody UserDTO userDto) {
        User user = userRepository.findByUsername(userDto.getUsername());
        if(user != null) {
            throw new RuntimeException("User already exists!");
        }
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(encodedPassword);
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
        return "Signup Successful";
    }
}

