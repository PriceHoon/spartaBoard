package com.sparta.spartaboard.controller;

import com.sparta.spartaboard.dto.CommentRequestDto;
import com.sparta.spartaboard.dto.CommentResponseDto;
import com.sparta.spartaboard.dto.ResponseMsgStatusCodeDto;
import com.sparta.spartaboard.entity.Comment;
import com.sparta.spartaboard.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글 기능 구현
    @PostMapping("/board/{id}/comment")
    public CommentResponseDto addComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request){

        return commentService.addComments(id,requestDto,request);
    }

    //해당 게시글의 모든 댓글 가져오기 오름차순
    @GetMapping("/board/{id}/comment")
    public List<CommentResponseDto> getComments(@PathVariable Long id,HttpServletRequest request){
        return commentService.getComments(id,request);
    }

    @PutMapping("/board/comment/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id,@RequestBody CommentRequestDto commentRequestDto,HttpServletRequest request){
        return commentService.update(id,commentRequestDto,request);
    }

    @DeleteMapping("/board/comment/{id}")
    public ResponseMsgStatusCodeDto deleteComment(@PathVariable Long id,HttpServletRequest request){
        return commentService.delete(id,request);
    }

}
