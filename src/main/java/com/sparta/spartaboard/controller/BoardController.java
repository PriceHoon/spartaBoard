package com.sparta.spartaboard.controller;


import com.sparta.spartaboard.dto.BoardRequestDTO;
import com.sparta.spartaboard.dto.BoardResponseDto;
import com.sparta.spartaboard.entity.Board;
import com.sparta.spartaboard.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/list")
    public List<BoardResponseDto> getBoardList(){
        return boardService.getBoardAll();
    }

    //Board(게시물) 추가시 해당 User의 ID를 body로 받아서 ~ 같이 추가해줘야 될 것 같음.
    @PostMapping("/board/list")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDTO boardDto, HttpServletRequest request){

        return boardService.createBoard(boardDto,request);
    }

    @GetMapping("/board/list/{id}")
    public Optional<BoardResponseDto> findBoard(@PathVariable Long id){
        return boardService.findBoardById(id);
    }

    @PutMapping("/board/list/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDTO boardDto , HttpServletRequest request){
        return boardService.update(id,boardDto,request);
    }

    @DeleteMapping("/board/list/{id}")
    public ResponseEntity deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDTO boardDto, HttpServletRequest request){
        return boardService.delete(id,boardDto,request);
    }



}
