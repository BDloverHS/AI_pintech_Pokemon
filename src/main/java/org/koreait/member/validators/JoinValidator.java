package org.koreait.member.validators;

import org.koreait.member.controllers.RequestAgree;
import org.koreait.member.controllers.RequestJoin;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Lazy
@Component
public class JoinValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestAgree.class) || clazz.isAssignableFrom(RequestJoin.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof RequestAgree requestAgree) {
            validateAgree(requestAgree,errors);
        } else {
            validateJoin((RequestJoin)target, errors);
        }
    }

    /**
     * 약관 동의 검증
     *
     * @param form
     * @param errors
     */
    private void validateAgree(RequestAgree form, Errors errors) {

    }

    /**
     * 회원 가입 검증
     *
     * @param form
     * @param errors
     */
    private void validateJoin(RequestJoin form, Errors errors) {
        /**
         * 이메일 중복 여부 체크
         * 비밀번호 복잡성 - 알파벳 대소문자 1개 이상, 숫자 1개이상, 특수문자 포함
         * 비밀번호, 비밀번호 확인 일치 여부
         * 생년월일을 입력받으면 14세 이상만 가입 가능하게 통제
         */
    }
}
