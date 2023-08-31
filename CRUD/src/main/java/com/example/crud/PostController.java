package com.example.crud;

import com.example.crud.dto.PostDto;
import com.example.crud.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
//@RequestMapping("/post")
@Slf4j
public class PostController {
   // private final List<PostDto> postList;
    private final PostService postService;

    public PostController(@Autowired PostService postService){
       // postList = new ArrayList<>();
        this.postService = postService;

    }

    @PostMapping("/create")
    public void createPost(@RequestBody PostDto postDto){
        this.postService.createPost(postDto);
        log.info(postDto.toString());
    }
    @GetMapping("read-all")
    public List<PostDto> readPostAll(){
        log.info("read all");
        return this.postService.readPostAll();
    }
    @GetMapping("read-one")
    public PostDto readPostOne(@RequestParam("id") int id){
        log.info("read one");
        return this.postService.readPostOne(id);
    }
    @PostMapping("/update")
    public void updatePost(
            @RequestParam("id") int id,
            @RequestBody PostDto postDto
    ){
        log.info("");
        this.postService.updatePost(id,postDto);
    }

    @DeleteMapping("delete")
    public void deletePost(@RequestParam("id") int id){
        log.info("delete");
        this.postService.deletePost(id);
    }

}
