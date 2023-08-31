package dev.example.jpa.repository;


import dev.example.jpa.dto.BoardDto;

import java.util.Collection;

public interface BoardRepository  {//< 어떤 엔티티냐, 아이디가 어떤 타입이냐 >
    BoardDto create(BoardDto dto);
    BoardDto read(Long id);
    Collection<BoardDto> readAll();
    boolean update(Long id, BoardDto dto); // boolean 이유 정상적으로 잘 작동하는지만 알기 위해
    boolean delete(Long id); // boolean 이유 정상적으로 잘 작동하는지만 알기 위해

}
