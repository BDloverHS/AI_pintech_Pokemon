package org.koreait.message.validators;

import lombok.RequiredArgsConstructor;
import org.koreait.member.libs.MemberUtil;
import org.koreait.member.repositories.MemberRepository;
import org.koreait.message.controllers.RequestMessage;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Lazy
@Component
@RequiredArgsConstructor
public class MessageValidator implements Validator {

    private final MemberUtil memberUtil; // 관리자인지 아닌지 체크하기 위함
    private final MemberRepository memberRepository;


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestMessage.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RequestMessage form = (RequestMessage) target;
        String email = form.getEmail();

        boolean notice = form.isNotice();
        if (!memberUtil.isAdmin() && notice) { // 관리자가 아닌 사람이 쓰는 쪽지가 공지 쪽지이면 안 됨
            notice = false;
            form.setNotice(notice);
        }

        // 관리자가 아닌 사람이 쓰는 족지가 공지 쪽지는 아니지만 이메일이 없어선 안 됨

        if (!memberUtil.isLogin() && !notice && !StringUtils.hasText(email)) {
            errors.rejectValue("email", "NotBlank");
        }

        if (!notice && !memberRepository.exists(email)) {
            errors.reject("NotFound.member");
        }
    }
}
