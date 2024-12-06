package org.koreait.mypage.controllers;

import lombok.Data;

@Data
public class RequestProfile {
    private String name; // 회원명
    private String nickName; // 닉네임
    private String password; // 비밀번호
    private String confirmPassword;

}
