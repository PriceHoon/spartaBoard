package com.sparta.spartaboard.entity;


import com.sparta.spartaboard.dto.SignUpDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name="users")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private String pwd;

    @OneToMany
    List<Board> boardList = new ArrayList<>();

    public User(SignUpDto signUpDto) {
        this.username = signUpDto.getUsername();
        this.pwd = signUpDto.getPwd();
    }
}
