package com.sparta.spartaboard.entity;


import com.sparta.spartaboard.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String contents;


    //(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID",nullable = false)
    private Board board;

    public Comment(CommentRequestDto requestDto,User user, Board board) {
        this.contents = requestDto.getContents();
        this.user = user;
        this.board = board;
    }

    public void update(CommentRequestDto requestDto){
        this.contents = requestDto.getContents();
    }
}
