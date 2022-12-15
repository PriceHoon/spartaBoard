package com.sparta.spartaboard.service;


import com.sparta.spartaboard.dto.LoginRequestDto;
import com.sparta.spartaboard.dto.SignUpDto;
import com.sparta.spartaboard.entity.User;
import com.sparta.spartaboard.jwt.JwtUtil;
import com.sparta.spartaboard.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;


    public void signup(SignUpDto signUpDto){

        String username = signUpDto.getUsername();

        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        User user = new User(signUpDto);
        userRepository.save(user);
    }

    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response){

        String username = loginRequestDto.getUsername();
        String pwd = loginRequestDto.getPwd();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("일치하는 회원이 없습니다!")
        );

        if(!user.getPwd().equals(pwd)){
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));
    }
}
