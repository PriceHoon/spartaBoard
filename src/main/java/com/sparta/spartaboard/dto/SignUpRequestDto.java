package com.sparta.spartaboard.dto;


import com.sparta.spartaboard.entity.UserRoleEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignUpRequestDto {

    @NotNull(message = "이름은 필수 값 입니다!!")
    @Size(min = 4,max = 10)
    @Pattern(regexp = "^([a-z]+[0-9]+)$")
    private String username;

    @NotNull(message = "비밀번호는 필수 값 입니다!!")
    @Size(min = 8,max = 15)
    @Pattern(regexp = "^([a-zA-Z]+[0-9]+)$")
    private String pwd;

    private String adminToken = "";

    private UserRoleEnum userRoleEnum;
}
