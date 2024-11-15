package org.koreait.member.controllers;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class RequestJoin {
    private boolean[] requiredTerms; // 필수 약관 동의 여부 / 반드시 모두 선택을 해야하므로 갯수 체크만으로도 충분
    private List<String> optionalTerms; // 선택 약관 동의 여부 / 선택 약관은 어떤 약관인지 구분할 수 있어야 함

    private String email; // 이메일
    private String name; // 회원명
    private String password; // 비밀번호
    private String confirmPassword; // 비밀번호 확인
    private String nickName; // 닉네임
    private LocalDate birthDt; // 생년월일
}
