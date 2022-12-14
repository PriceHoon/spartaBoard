package com.sparta.spartaboard.service;


import com.sparta.spartaboard.dto.SignUpDto;
import com.sparta.spartaboard.entity.User;
import com.sparta.spartaboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signup(SignUpDto signUpDto){

        User user = new User(signUpDto);
       userRepository.save(user);
    }
}
