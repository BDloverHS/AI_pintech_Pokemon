package org.koreait.member.controllers;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class RequestAgree {
    // @AssertTrue
    private boolean requiredTerms1;

    // @AssertTrue
    private boolean requiredTerms2;

    // @AssertTrue
    private boolean requiredTerms3;

    private List<String> optionalTerms; // 선택 약관 동의 여부 / 선택 약관은 어떤 약관인지 구분할 수 있어야 함
}
