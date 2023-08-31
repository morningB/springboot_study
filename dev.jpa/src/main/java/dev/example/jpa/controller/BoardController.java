package dev.example.jpa.controller;

import dev.example.jpa.dto.BoardDto;
import dev.example.jpa.dto.ValidTestDto;
import dev.example.jpa.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("board")
@RestController
@Slf4j
public class BoardController {
    private final BoardRepository boardRepository;
    public BoardController(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }
    @PostMapping()
    public ResponseEntity<BoardDto> create(@RequestBody BoardDto dto){
        return ResponseEntity.ok(boardRepository.create(dto));
    }
    @GetMapping("{id}")
    public ResponseEntity<BoardDto> readBoard(
            @PathVariable Long id
    ){
        BoardDto dto = boardRepository.read(id);
        if(dto == null){
          return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }
    @GetMapping()
    public ResponseEntity<Collection<BoardDto>> readBoardAll(){
        return ResponseEntity.ok(this.boardRepository.readAll());
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateBoard(
            @PathVariable Long id,
            @RequestBody BoardDto dto
    ){
        if(!boardRepository.update(id, dto))
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBoard(
            @PathVariable Long id
    ){
        if(!boardRepository.delete(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("test-log")
    public void testLog(){
        log.trace("trace log message");
        log.debug("debug message");
        log.info("info message");
        log.warn("warn message");
        log.error("error message");

    }

}
