package com.sparta.spartaboard.entity;


import com.sparta.spartaboard.dto.SignUpRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    //아직은 쓸지 안쓸지 고민중
    //원래의도 -> 회원을 검증해서 그 회원이 작성한 게시물을 List형식으로 User가 갖고 있을 수 있게 하기 위함이였음..
//    @OneToMany
//    List<Board> boardList = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRoleEnum;

    public User(SignUpRequestDto signUpDto) {
        this.username = signUpDto.getUsername();
        this.pwd = signUpDto.getPwd();
        this.userRoleEnum = signUpDto.getUserRoleEnum();
    }
}
