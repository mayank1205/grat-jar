package com.gratjar.notes.controller;

import com.gratjar.notes.PostMapper;
import com.gratjar.notes.entity.Post;
import com.gratjar.notes.model.PostDTO;
import com.gratjar.notes.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts().stream().map(post -> PostMapper.toPostDTO(post)).toList());
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody Post post) {
        Post savedPost = postService.createPost(post);
        return ResponseEntity.status(201).body(PostMapper.toPostDTO(savedPost));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long id) {
        return postService.getPostById(id).map(post-> PostMapper.toPostDTO(post))
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
