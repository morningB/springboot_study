package dev.example.jpa.dto;


import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDto {
    private Long id;
    @NotNull(message = "title not null")
    private String title;
    @Size(max = 40,message = "size exceed")
    private String content;
    @Size(min = 3,max = 10,message = "writer name error")
    private String writer;
    private String password;
    private Long boardId;


    public PostDto passwordMasked(){
        return new PostDto(
                this.id,
                this.title,
                this.content,
                this.writer,
                "******",
                this.boardId
        );
    }

}
