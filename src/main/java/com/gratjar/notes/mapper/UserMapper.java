package com.gratjar.notes.mapper;

import com.gratjar.notes.PostMapper;
import com.gratjar.notes.entity.Post;
import com.gratjar.notes.entity.User;
import com.gratjar.notes.model.UserDTO;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());

        if (user.getPosts() != null) {
            dto.setPosts(user.getPosts()
                    .stream()
                    .map(PostMapper::toPostDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public static User toUserEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());

        if (dto.getPosts() != null) {
            user.setPosts(dto.getPosts()
                    .stream()
                    .map(postDTO -> {
                        Post post = PostMapper.toPostEntity(postDTO);
                        post.setUser(user); // maintain relationship
                        return post;
                    })
                    .collect(Collectors.toList()));
        }

        return user;
    }
}
