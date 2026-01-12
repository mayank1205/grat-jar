package com.gratjar.notes.service;

import com.gratjar.notes.entity.Post;
import com.gratjar.notes.entity.User;
import com.gratjar.notes.repository.PostRepository;
import com.gratjar.notes.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public List<Post> getAllPosts(User user) {
        return postRepository.findAllByUser(user);
    }

    public Optional<Post> getPostById(Long id, User user) {
        return postRepository.findByUserAndId(user, id);
    }

    public Post createPost(Post post, User user) {
        post.setUser(user);
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return postRepository.existsById(id);
    }
}
