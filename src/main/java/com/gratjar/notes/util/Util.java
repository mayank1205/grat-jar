package com.gratjar.notes.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gratjar.notes.entity.User;
import com.gratjar.notes.service.PostService;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class Util {

    @Autowired
    PostService postService;

    @Autowired
    JwtUtil jwtUtil;
    
    public User getUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        return postService.getUser(jwtUtil.extractUsername(token));
    }
}
