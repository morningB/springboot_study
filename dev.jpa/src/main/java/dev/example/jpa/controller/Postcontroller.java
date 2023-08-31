package dev.example.jpa.controller;

import dev.example.jpa.aspect.LogExecutionTime;
import dev.example.jpa.aspect.LogParameters;
import dev.example.jpa.dto.PostDto;
import dev.example.jpa.exception.PostNotExistException;
import dev.example.jpa.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("board/{boardId}/post") // 특정한 보드 안에잇는 post를 찾기 위해 이런식으로 함
public class Postcontroller {
    private final PostRepository postRepository;

    public Postcontroller(
            @Autowired
            PostRepository postRepository
    ){
        this.postRepository = postRepository;
    }


    @LogParameters
    @PostMapping()
    public ResponseEntity<PostDto> createPost(

            @PathVariable("boardId") Long boardId,
            @Valid @RequestBody PostDto postDto

    ){
        PostDto result = this.postRepository.create(boardId,postDto);
        return ResponseEntity.ok(result.passwordMasked());  // password를 가린다
    }
    @GetMapping("{postId}")
    public ResponseEntity<PostDto> readPost(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId
    ){
        PostDto result = this.postRepository.read(boardId,postId);
        if(result == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(result.passwordMasked());

    }
    @LogExecutionTime
    @GetMapping()
    public ResponseEntity<Collection<PostDto>> readPostAll(
            @PathVariable("boardId") Long boardId
    ){
        Collection<PostDto> postList = this.postRepository.readAll(boardId);
        if(postList == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(postList);
    }
    @PutMapping("{postId}")
    public ResponseEntity<?> updatePost(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId,
            @RequestBody PostDto dto

    ){
        if(!postRepository.update(boardId,postId,dto))
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();

    }
    @DeleteMapping("{postId}")
    public ResponseEntity<?> deletePost(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId,
            @RequestParam("password") String password
    ){
        if(!postRepository.delete(boardId,postId,password))
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();

    }

    @GetMapping("test-exception")
    public void testsss(){
        throw new PostNotExistException();
    }
}
