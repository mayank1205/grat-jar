package com.gratjar.notes.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String email;

    @JsonIgnore
    private String password;
    private List<PostDTO> posts;

    // Getters and Setters
}

