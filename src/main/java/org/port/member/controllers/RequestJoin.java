package org.port.member.controllers;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.port.member.constants.Gender;
import org.port.member.social.constants.SocialChannel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Data
public class RequestJoin extends RequestAgree {
    @Email
    @NotBlank
    private String email; // 이메일

    @NotBlank
    private String name; // 회원명

    @Size(min=8)
    private String password; // 비밀번호 // 소셜 로그인을 할 땐 null이어도 문제 없음

    @NotBlank
    private String confirmPassword; // 비밀번호 확인

    @NotBlank
    private String nickName; // 닉네임

    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDt; // 생년월일

    @NotNull
    private Gender gender; // 성별

    @NotBlank
    private String zipCode; // 우편번호

    @NotBlank
    private String address; // 주소
    private String addressSub; // 나머지 주소

    private SocialChannel socialChannel;
    private String socialToken;

    // 소셜 로그인으로 가입하는 건지 체크
    public boolean isSocial() {
        return socialChannel != null && socialChannel != SocialChannel.NONE && StringUtils.hasText(socialToken);
    }

}