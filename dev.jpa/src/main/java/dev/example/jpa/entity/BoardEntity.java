package dev.example.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @OneToMany(
            targetEntity = PostEntity.class,   // 말 그대로 타켓
            fetch = FetchType.LAZY, // 어떤식으로 불러올지 (그냥 기본값)
            mappedBy = "boardEntity" // postEntity에 있는 BoardEntity의 변수명과 동일하게 해준다.
    )
    private List<PostEntity> postEntityList = new ArrayList<>();

}
