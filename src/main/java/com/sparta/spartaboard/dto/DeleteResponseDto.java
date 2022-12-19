package com.sparta.spartaboard.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteResponseDto {

    //사실 그냥 로그인,회원가입,삭제등 상태메세지와 코드를 내려주는 dto를 하나로 통합해도 된다. 어차피 속성 필드는 똑같잖아..
    private String msg;
    private int StatusCode;
}
