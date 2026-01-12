package com.gratjar.notes;

import com.gratjar.notes.entity.Post;
import com.gratjar.notes.model.PostDTO;

public class PostMapper {
    public static PostDTO toPostDTO(Post post) {
        if (post == null) return null;

        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());

        return dto;
    }

    public static Post toPostEntity(PostDTO dto) {
        if (dto == null) return null;

        Post post = new Post();
        post.setId(dto.getId());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        return post;
    }
}



