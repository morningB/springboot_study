package com.example.crud.reposiory;

import com.example.crud.dto.PostDto;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PostRepository {
    List<PostDto> findAll();
    PostDto findById(int id);
    boolean save(PostDto postDto);
    boolean delete(int id);
    boolean update(int id, PostDto postDto);



}
