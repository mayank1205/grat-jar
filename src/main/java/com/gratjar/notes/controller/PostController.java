package com.gratjar.notes.controller;

import com.gratjar.notes.PostMapper;
import com.gratjar.notes.entity.Post;
import com.gratjar.notes.model.PostDTO;
import com.gratjar.notes.service.PostService;
import com.gratjar.notes.util.Util;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private Util util;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts(HttpServletRequest request) {
        return ResponseEntity.ok(postService.getAllPosts(util.getUser(request)).stream().map(post -> PostMapper.toPostDTO(post)).toList());
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(HttpServletRequest request, @RequestBody Post post) {
        
        Post savedPost = postService.createPost(post, util.getUser(request));
        return ResponseEntity.status(201).body(PostMapper.toPostDTO(savedPost));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPost(HttpServletRequest request, @PathVariable Long id) {
        return postService.getPostById(id, util.getUser(request)).map(post-> PostMapper.toPostDTO(post))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        if (postService.existsById(id)) {
            postService.deletePost(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
