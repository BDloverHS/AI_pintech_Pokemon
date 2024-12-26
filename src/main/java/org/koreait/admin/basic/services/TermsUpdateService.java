package org.koreait.admin.basic.services;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.koreait.global.entities.Terms;
import org.koreait.global.exceptions.script.AlertException;
import org.koreait.global.libs.Utils;
import org.koreait.global.services.CodeValueService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Lazy
@Service
@RequiredArgsConstructor
public class TermsUpdateService {

    private final CodeValueService service;
    private final HttpServletRequest request;

    private final Utils utils;

    /**
     * 약관 저장
     *
     * @param terms
     */
    public void save(Terms terms) {
        String code = String.format("term_%s", terms.getCode());
        service.save(code, terms);
    }

    /**
     *
     * @param chks
     */
    public void processList(List<Integer> chks) {
        String mode = request.getMethod().equalsIgnoreCase("DELETE") ? "delete" : "modify";
        if (chks == null || chks.isEmpty()) {
            throw new AlertException(String.format("%s할 약관을 선택하세요.", mode.equals("delete") ? "삭제":"수정"));
        }

        for (int chk : chks) {
            String code = utils.getParam("code_" + chk);
            String subject = utils.getParam("subject_" + chk);
            String content = utils.getParam("content" + chk);

            if (mode.equals("delete")) { //  삭제
                deleteCodes.add(String.format("terms_%s", code));
            } else { // 수정
                Terms terms = Terms.builder()
                        .code(code)
                        .subject(subject)
                        .content(content)
                        .build();
                save(terms);
            }
        } // for 끝
        if (!deleteCodes.isEmpty()) {
            service.remove(deleteCodes);
        }
    }
}