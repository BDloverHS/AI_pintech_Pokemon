package org.koreait.email.controllers;

import lombok.Data;

import java.util.List;

@Data
public class RequestEmail {
    private List<String> to; // 받는 쪽 이메일(여러 명이 가능하도록 리스트 형식)
    private List<String> cc; // 참조
    private List<String> bcc; // 숨은 참조
    private String subject; // 메일 제목
    private String content; // 메일 내용

}
