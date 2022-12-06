package com.sparta.spartaboard.controller;


import com.sparta.spartaboard.dto.BoardRequestDTO;
import com.sparta.spartaboard.entity.Board;
import com.sparta.spartaboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RestController

public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/list")
    public List<Board> getBoardList(){
        return boardService.getBoardAll();
    }

    @PostMapping("/board/list")
    public Board createBoard(@RequestBody BoardRequestDTO boardDto){
        return boardService.createBoard(boardDto);
    }

    @GetMapping("/board/list/{id}")
    public Optional<Board> findBoard(@PathVariable Long id){
        return boardService.findBoardById(id);
    }

    @PutMapping("/board/list/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDTO boardDto){
        return boardService.update(id,boardDto);
    }

    @DeleteMapping("/board/list/{id}")
    public Long deleteBoard(@PathVariable Long id,@RequestBody BoardRequestDTO boardDto){
        return boardService.delete(id,boardDto);
    }

}
