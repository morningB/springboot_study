package dev.example.jpa.repository;

import dev.example.jpa.dto.PostDto;
import dev.example.jpa.entity.BoardEntity;
import dev.example.jpa.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


public interface PostRepository {
    PostDto create(Long boradId,PostDto dto);
    PostDto read(Long boradId,Long postId);
    Collection<PostDto> readAll(Long boardId);
    boolean update(Long boardId,Long postId,PostDto dto);
    boolean delete(Long boardId,Long postId,String password);
}
