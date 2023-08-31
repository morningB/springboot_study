package dev.example.jpa.repository;

import dev.example.jpa.dto.BoardDto;
import dev.example.jpa.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PostRepositoryImp implements PostRepository{

    private final BoardRepository boardRepository;
    private final Map<Long,PostDto> memory = new HashMap<>();
    private Long lastIndex=0L;

    public PostRepositoryImp(@Autowired BoardRepository boardRepository){
        this.boardRepository=boardRepository;
    }
    @Override
    public PostDto create(Long boardId, PostDto dto) {
        BoardDto boardDto = this.boardRepository.read(boardId);
        if(boardDto == null){
            return null;
        }
        dto.setBoardId(boardId);
        lastIndex++;
        dto.setId(lastIndex);
        memory.put(lastIndex,dto);
        return dto;
    }

    @Override
    public PostDto read(Long boardId, Long postId) {
        PostDto postDto = memory.getOrDefault(postId,null);
        if(postDto == null){
            return null;
        }else if(!Objects.equals(postDto.getBoardId(),boardId)){
            return null;
        }
        return postDto;
    }

    @Override
    public Collection<PostDto> readAll(Long boardId) {
        if(boardRepository.read(boardId)==null) return null;
        Collection<PostDto> postList = new ArrayList<>();
        memory.forEach((postId,postDto) -> {
            if(Objects.equals(postDto.getBoardId(),boardId))
                postList.add(postDto);

        });
        return postList;
    }

    @Override
    public boolean update(Long boardId, Long postId, PostDto dto) {
        PostDto target = memory.getOrDefault(postId,null);
        if(target == null){
            return false;
        }
        else if(!Objects.equals(target.getBoardId(),boardId)){
            return false;
        }else if (!Objects.equals(target.getPassword(), dto.getPassword())){
            return false;
        }
        target.setTitle(
                dto.getTitle() == null ? target.getTitle() : dto.getTitle());
        target.setContent(
                dto.getContent() == null ? target.getContent() : dto.getContent());
        return true;
    }

    @Override
    public boolean delete(Long boardId, Long postId, String password) {
        PostDto target = memory.getOrDefault(postId,null);
        if(target == null){
            return false;
        }
        else if(!Objects.equals(target.getBoardId(), boardId)){
            return false;
        }else if (!Objects.equals(target.getPassword(), password)) {
            return false;
        }

        memory.remove(postId);
        return true;

    }
}
