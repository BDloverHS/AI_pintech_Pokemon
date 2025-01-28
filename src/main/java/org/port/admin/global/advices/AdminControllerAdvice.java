package org.port.admin.global.advices;

import lombok.RequiredArgsConstructor;
import org.port.member.entities.Member;
import org.port.member.libs.MemberUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@RequiredArgsConstructor
@ControllerAdvice("org.koreait.admin")
public class AdminControllerAdvice {
    private final MemberUtil memberUtil;

    @ModelAttribute("profile")
    public Member profile() {
        return memberUtil.getMember();
    }
}
