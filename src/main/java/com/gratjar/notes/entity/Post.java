package com.gratjar.notes.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "posts")
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and Setters
}
