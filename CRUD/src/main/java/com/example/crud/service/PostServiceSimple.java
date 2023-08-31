package com.example.crud.service;

import com.example.crud.dto.PostDto;
import com.example.crud.reposiory.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceSimple implements PostService{
    private final PostRepository postRepository;

    public PostServiceSimple(@Autowired PostRepository postRepository){
        this.postRepository=postRepository;
    }
    @Override
    public void createPost(PostDto postDto) {
        //TODO
        if(!this.postRepository.save(postDto)){
            throw new RuntimeException("save error");
        }

    }

    @Override
    public List<PostDto> readPostAll() {
        return this.postRepository.findAll();
    }

    @Override
    public PostDto readPostOne(int id) {
        return this.postRepository.findById(id);
    }

    @Override
    public void updatePost(int id, PostDto postDto) {
        this.postRepository.update(id,postDto);
    }

    @Override
    public void deletePost(int id) {
        this.postRepository.delete(id);
    }
}
