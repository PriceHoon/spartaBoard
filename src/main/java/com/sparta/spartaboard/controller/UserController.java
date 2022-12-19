package com.sparta.spartaboard.controller;

import com.sparta.spartaboard.dto.LoginRequestDto;
import com.sparta.spartaboard.dto.SignUpRequestDto;
import com.sparta.spartaboard.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")

public class UserController {


    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity singup(@Valid @RequestBody SignUpRequestDto signUpRequestDto , BindingResult bindingResult){


        //아이디 패스워드의 형식이 맞는지 검증 (완료)
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        return userService.signup(signUpRequestDto);

    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){

        return userService.login(loginRequestDto,response);
    }
}
