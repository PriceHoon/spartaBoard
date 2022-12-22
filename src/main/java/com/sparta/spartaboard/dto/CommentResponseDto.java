package com.sparta.spartaboard.dto;

import com.sparta.spartaboard.entity.Board;
import com.sparta.spartaboard.entity.Comment;
import com.sparta.spartaboard.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private Long id;
    private String contents;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();

    }

}
