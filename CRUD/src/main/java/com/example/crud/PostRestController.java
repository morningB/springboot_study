package com.example.crud;


import com.example.crud.dto.PostDto;
import com.example.crud.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("post")
@Slf4j
public class PostRestController {
    //private final List<PostDto> postList ;
    private final PostService postService;
    public PostRestController(@Autowired PostService postService){
        //postList = new ArrayList<>();
        this.postService = postService;
    }
    //http://localhost:8080/post까지 접근한 것
    //Post / post경로에 따라 Requsstbody를 요청
    @PostMapping() // 없었던 자원을 새로 생성하는 것이다.
    @ResponseStatus(HttpStatus.CREATED) // http 상태를 알려주는 어노태이션
    public void createPost(@RequestBody PostDto postDto){
        log.info(postDto.toString());

        this.postService.createPost(postDto);
    }
    //http://localhost:8080/post까지 접근한 것
    //Get / post
    @GetMapping()
    public List<PostDto> readAllPost(){
        log.info("read all post");
        return this.postService.readPostAll();
    }

    // Get /post/id
    // Get /post?id=0

    @GetMapping("{id}")
    public PostDto readPost(@PathVariable int id){
        log.info("read on " + id);
        return this.postService.readPostOne(id);
    }
    @PutMapping("{id}") // 현재 보낸 데이터를 다시 그 위치에 넣는다 한마디로 수정(update)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePost(@PathVariable int id,
                           @RequestBody PostDto postDto){
        this.postService.updatePost(id,postDto);
    }
    // Delete /post/id
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePost(@PathVariable int id){
        this.postService.deletePost(id);
    }

}
