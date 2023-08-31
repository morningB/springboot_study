package com.example.crud.reposiory;

import com.example.crud.dto.PostDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class PostRepositoryInMemory implements PostRepository{

    private final List<PostDto> postList;
    public PostRepositoryInMemory(){
        postList=new ArrayList<>();
    }
    @Override
    public List<PostDto> findAll() {
        return this.postList;
    }

    @Override
    public PostDto findById(int id) {
        return this.postList.get(id);
    }

    @Override
    public boolean save(PostDto postDto) {
        return this.postList.add(postDto);
    }

    @Override
    public boolean delete(int id) {
        this.postList.remove(id);
        return true;
    }

    @Override
    public boolean update(int id,PostDto postDto) {
        PostDto target = this.postList.get(id);
        if(postDto.getTitle() != null){
            target.setTitle(postDto.getTitle());
        }
        if(postDto.getContent() != null){
            target.setContent(postDto.getContent());
        }
        this.postList.set(id,target);
        return true;
    }
}
