package com.sparta.spartaboard.service;


import com.sparta.spartaboard.dto.LoginRequestDto;
import com.sparta.spartaboard.dto.SignUpRequestDto;
import com.sparta.spartaboard.dto.SignUpAndLoginResponseDto;
import com.sparta.spartaboard.entity.User;
import com.sparta.spartaboard.entity.UserRoleEnum;
import com.sparta.spartaboard.jwt.JwtUtil;
import com.sparta.spartaboard.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";


    public ResponseEntity signup(SignUpRequestDto signUpDto){

        String username = signUpDto.getUsername();

        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        if(signUpDto.getAdminToken().equals(ADMIN_TOKEN)){
            signUpDto.setUserRoleEnum(UserRoleEnum.ADMIN);
        }else{
            signUpDto.setUserRoleEnum(UserRoleEnum.USER);
        }

        User user = new User(signUpDto);
        userRepository.save(user);

        SignUpAndLoginResponseDto signUpAndLoginResponseDto = new SignUpAndLoginResponseDto();
        signUpAndLoginResponseDto.setMsg("회원가입 완료!");
        signUpAndLoginResponseDto.setStatusCode(HttpStatus.OK.value());

        return ResponseEntity.status(HttpStatus.OK).body(signUpAndLoginResponseDto);
    }

    public ResponseEntity login(LoginRequestDto loginRequestDto, HttpServletResponse response){

        String username = loginRequestDto.getUsername();
        String pwd = loginRequestDto.getPwd();


        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("일치하는 회원이 없습니다!")
        );

        if(!user.getPwd().equals(pwd)){
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        SignUpAndLoginResponseDto signUpAndLoginResponseDto = new SignUpAndLoginResponseDto();
        signUpAndLoginResponseDto.setMsg("로그인 완료!");
        signUpAndLoginResponseDto.setStatusCode(HttpStatus.OK.value());

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(),user.getUserRoleEnum()));
        return ResponseEntity.status(HttpStatus.OK).body(signUpAndLoginResponseDto);
    }
}
