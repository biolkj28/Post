package com.sparta.post.dto;

import lombok.*;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String nickname;
    private String password;
    private String email;
    private String checkPw;
    private boolean admin = false;
    private String adminToken = "";

    public SignupRequestDto(String username, String nickname, String password, String email, String checkPw) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.checkPw = checkPw;
    }
}