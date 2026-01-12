package com.gratjar.notes.repository;

import com.gratjar.notes.entity.Post;
import com.gratjar.notes.entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUser(User user);

    public Optional<Post> findByUserAndId(User user, Long id);
}
