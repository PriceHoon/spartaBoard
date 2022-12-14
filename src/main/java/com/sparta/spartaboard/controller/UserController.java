package com.sparta.spartaboard.controller;

import com.sparta.spartaboard.dto.SignUpDto;
import com.sparta.spartaboard.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity singup(@Valid @RequestBody SignUpDto signUpDto , BindingResult bindingResult){


        //아이디 패스워드의 형식이 맞는지 검증 (완료)
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        userService.signup(signUpDto);

        return ResponseEntity.ok(signUpDto);

    }
}
