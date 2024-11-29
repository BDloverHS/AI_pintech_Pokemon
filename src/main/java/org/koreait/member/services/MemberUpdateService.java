package org.koreait.member.services;

import lombok.AllArgsConstructor;
import org.koreait.member.controllers.RequestJoin;
import org.koreait.member.repositories.AuthoritiesRepository;
import org.koreait.member.repositories.MemberRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
@Lazy // 자연로딩 - 최초로 빈을 사용할 때 생성
@Service
@AllArgsConstructor
public class MemberUpdateService {


    private final MemberRepository memberRepository;
    private final AuthoritiesRepository authoritiesRepository;


    /**
     * 커맨드 객체의 타입에 따라서 RequestJoin이면 회원 가입 처리 RequestProfile이면 회원정보 수정 처리
     * @param form
     */
    public void process(RequestJoin form) {
        // 커맨드 객체에서 엔티티 객체로 데이터 옮기기

    }

    /**
     * 회원정보 추가 또는 수정 처리
     */
    private void save() {

    }
}
