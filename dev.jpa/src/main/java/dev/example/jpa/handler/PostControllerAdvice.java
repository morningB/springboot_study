package dev.example.jpa.handler;

import dev.example.jpa.exception.BaseException;
import dev.example.jpa.exception.ErrorResponseDto;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostControllerAdvice {
    @ExceptionHandler(BaseException.class)
    public  ErrorResponseDto handlerException(BaseException exception){
        return new ErrorResponseDto(exception.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseDto handleValidException(MethodArgumentNotValidException exception){
        return new ErrorResponseDto(exception.getMessage());
    }
}
