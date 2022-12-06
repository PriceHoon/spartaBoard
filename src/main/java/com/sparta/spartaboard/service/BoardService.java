package com.sparta.spartaboard.service;


import com.sparta.spartaboard.dto.BoardRequestDTO;
import com.sparta.spartaboard.entity.Board;
import com.sparta.spartaboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public List<Board> getBoardAll() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }
    @Transactional
    public Board createBoard(BoardRequestDTO boardDto) {
        Board board = new Board(boardDto);
        boardRepository.save(board);
        return board;
    }
    @Transactional
    public Optional<Board> findBoardById(Long id) {
        return Optional.ofNullable(boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("특정 게시글이 없습니다!")
        ));
    }
    @Transactional
    public Long update(Long id,BoardRequestDTO boardDto) {
       Board board = boardRepository.findById(id).orElseThrow(
               () -> new IllegalArgumentException("수정하고자 하는 게시글이 없습니다!")
       );

       board.update(boardDto);
       return board.getId();
    }

    public Long delete(Long id,BoardRequestDTO boardDto) {

       Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("수정하고자 하는 게시글이 없습니다!")
        );
       if(!board.getPwd().equals(boardDto.getPwd())){
           throw new IllegalArgumentException("비밀번호가 틀립니다!");
       }
       boardRepository.deleteById(id);
        return id;
    }
}
