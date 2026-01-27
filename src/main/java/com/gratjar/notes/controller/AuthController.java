package com.gratjar.notes.controller;

import com.gratjar.notes.entity.User;
import com.gratjar.notes.exception.ResourceNotFoundException;
import com.gratjar.notes.exception.UnauthorizedException;
import com.gratjar.notes.exception.UserAlreadyExistsException;
import com.gratjar.notes.model.ResponseModel;
import com.gratjar.notes.model.UserDTO;
import com.gratjar.notes.repository.UserRepository;
import com.gratjar.notes.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseModel> login(@RequestBody UserDTO userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        User user = userRepository.findByUsername(username);

        if(user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        if (user.getUsername().equals(username) && passwordEncoder.matches(password, user.getPassword())) {
            ResponseModel response = new ResponseModel();
            response.setMessage("Login Successful");
            response.setStatus(200);
            return ResponseEntity.ok().header("Authorization", jwtUtil.generateToken(username)).body(response);
        }
        throw new UnauthorizedException("Invalid credentials");
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseModel> signup(@RequestBody UserDTO userDto) {
        User user = userRepository.findByUsername(userDto.getUsername());
        if(user != null) {
            throw new UserAlreadyExistsException("User already exists!");
        }
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        ResponseModel response = new ResponseModel();
        response.setMessage("Signup Successful");
        response.setStatus(201);

        user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(encodedPassword);
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
        return ResponseEntity.status(201).header("Authorization", jwtUtil.generateToken(userDto.getUsername())).body(response);
    }
}

