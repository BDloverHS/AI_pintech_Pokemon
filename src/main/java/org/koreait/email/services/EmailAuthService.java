package org.koreait.email.services;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.koreait.email.controllers.RequestEmail;
import org.koreait.email.exceptions.AuthCodeExpiredException;
import org.koreait.email.exceptions.AuthCodeMismatchException;
import org.koreait.global.exceptions.BadRequestException;
import org.koreait.global.libs.Utils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Profile("email")
@RequiredArgsConstructor
public class EmailAuthService {
    private final Utils utils;
    private final EmailService emailService;
    private final HttpSession session;

    /**
     *
     * @param to : 수신 쪽 이메일 주소
     * @return
     */
    public boolean sendCode(String to) {
        Random random = new Random();
        String subject = utils.getMessage("Email.authCode.subject");

        /**
         * 인증 코드는 5자리 정수
         * 만료시간을 3분으로 기록
         * 사용자의 입력을 검증하기 위해서 세션에 인증 코드와 만료 시간을 기록
         */
        int authCode = random.nextInt(99999); // 5자리 숫자를 뽑기 위함

        // 현재 시간 기준 60(초) * 3만큼까지 시간 설정
        long expired = Instant.EPOCH.getEpochSecond() + 60 * 3;

        session.setAttribute("authCode", authCode);
        session.setAttribute("expiredTime", expired);

        Map<String, Object> tplData = new HashMap<>();
        tplData.put("authCode", authCode);

        RequestEmail form = new RequestEmail();
        form.setTo(List.of(to));
        form.setSubject(subject);

        return emailService.sendEmail(form, "auth", tplData);
    }

    /**
     * 인증코드 검증
     *
     * @param code : 사용자가 입력한 인증코드
     */
    // null을 포함시키기 위해 String 대신 Integer 채용
    public void verify(Integer code) {
        if (code == null) {
            throw new BadRequestException(utils.getMessage("NotBlank.authCode"));
        }

        long expired = (long)session.getAttribute("expiredTime");
        int authCode = (int)session.getAttribute("authCode");

        long now = Instant.EPOCH.getEpochSecond();
        if (expired < now) { // 코드가 만료된 경우
            throw new AuthCodeExpiredException();
        }

        if (!code.equals(authCode)) { // 인증 코드가 일치하지 않는 경우
            throw new AuthCodeMismatchException();
        }
    }
}
