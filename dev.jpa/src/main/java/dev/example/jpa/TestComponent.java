package dev.example.jpa;

import dev.example.jpa.entity.BoardEntity;
import dev.example.jpa.entity.PostEntity;
import dev.example.jpa.repository.BoardRepository;
import dev.example.jpa.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestComponent {
    public TestComponent(
            @Autowired BoardRepository boardRepository,
            @Autowired PostRepository postRepository
                         )
    {

   /*     BoardEntity boardEntity = new BoardEntity();
        boardEntity.setName("new board");
        BoardEntity newBoardEntity = boardRepository.save(boardEntity); //
        System.out.println(newBoardEntity.getName());

        PostEntity postEntity = new PostEntity();
        postEntity.setTitle("new post");
        postEntity.setContent("wwwwwwwww");
        postEntity.setWriter("aw");
        postEntity.setBoardEntity(newBoardEntity);
        PostEntity newPostEntity = postRepository.save(postEntity);
       // System.out.println(postRepository.findAllByWriter("aw").size());

*/

    }
}
