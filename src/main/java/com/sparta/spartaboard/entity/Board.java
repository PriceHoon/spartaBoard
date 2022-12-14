package com.sparta.spartaboard.entity;
import com.sparta.spartaboard.dto.BoardRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String pwd;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    public Board(BoardRequestDTO boardDto) {
        this.title = boardDto.getTitle();
        this.username = boardDto.getUsername();
        this.pwd = boardDto.getPwd();
        this.contents = boardDto.getContents();
    }
    public void update(BoardRequestDTO boardDto){
        this.title = boardDto.getTitle();
        this.username = boardDto.getUsername();
        this.contents = boardDto.getContents();
    }
}
