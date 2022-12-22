package com.sparta.spartaboard.entity;
import com.sparta.spartaboard.dto.BoardRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;


    @Column(nullable = false)
    private String contents;

    //fetType Lazy를 사용하는 순간 예외 터짐
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public Board(BoardRequestDTO boardDto , User user) {
        this.title = boardDto.getTitle();
        this.username = boardDto.getUsername();
//        this.pwd = boardDto.getPwd();
        this.contents = boardDto.getContents();
        this.user = user;
    }
    public void update(BoardRequestDTO boardDto){
        this.title = boardDto.getTitle();
        this.contents = boardDto.getContents();
    }
}
