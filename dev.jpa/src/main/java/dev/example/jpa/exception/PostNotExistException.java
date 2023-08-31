package dev.example.jpa.exception;

public class PostNotExistException extends BaseException {
    public PostNotExistException(){
        super("post dosen't exiset");
    }
}
