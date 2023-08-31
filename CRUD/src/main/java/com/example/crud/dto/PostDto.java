package com.example.crud.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDto {
    private int id;
    private String title;
    private String content;
    private String writer;
    private int board;
}
