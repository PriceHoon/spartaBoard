package com.sparta.spartaboard.controller;

import com.sparta.spartaboard.dto.LoginRequestDto;
import com.sparta.spartaboard.dto.SignUpDto;
import com.sparta.spartaboard.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
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

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        userService.login(loginRequestDto,response);
        return "success";
    }
}
