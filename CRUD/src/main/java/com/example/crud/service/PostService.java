package com.example.crud.service;

import com.example.crud.dto.PostDto;

import java.util.List;

public interface PostService {
    void createPost(PostDto postDto);
    List<PostDto> readPostAll();
    PostDto readPostOne(int id);
    void updatePost(int id, PostDto postDto);
    void deletePost(int id);
}
