package dev.example.jpa;

import dev.example.jpa.exception.BaseException;
import dev.example.jpa.exception.ErrorResponseDto;
import dev.example.jpa.exception.PostNotInBoardexception;
import dev.example.jpa.exception.PostNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("except")
public class ExcepTestController {
    @GetMapping("{id}")
    public void throwException(@PathVariable int id){
        switch (id){
            case 1:
                throw  new PostNotExistException();
            case 2:
                throw new PostNotInBoardexception();
            default:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleException(BaseException baseException){
        return new ErrorResponseDto(baseException.getMessage());

    }
}
