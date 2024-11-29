package org.koreait.member.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.koreait.member.constants.Authority;
import org.koreait.member.controllers.RequestJoin;
import org.koreait.member.entities.Authorities;
import org.koreait.member.entities.Member;
import org.koreait.member.entities.QAuthorities;
import org.koreait.member.repositories.AuthoritiesRepository;
import org.koreait.member.repositories.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Lazy // 자연로딩 - 최초로 빈을 사용할 때 생성
@Service
@RequiredArgsConstructor
public class MemberUpdateService {


    private final MemberRepository memberRepository;
    private final AuthoritiesRepository authoritiesRepository;
    private final ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    /**
     * 커맨드 객체의 타입에 따라서 RequestJoin이면 회원 가입 처리 RequestProfile이면 회원정보 수정 처리
     * @param form
     */
    public void process(RequestJoin form) {
        // 커맨드 객체에서 엔티티 객체로 데이터 옮기기
        Member member = modelMapper.map(form, Member.class);

        // 선택 약관 -> 약관 항목1 || 약관 항목2 || ...
        List<String> optinalTerms = form.getOptionalTerms();
        if (optinalTerms != null) {
            String _optinalTerms = optinalTerms.stream().collect(Collectors.joining("||"));
            member.setOptionalTerms(_optinalTerms);
        }

        // 비밀번호 해시화 -BCrypt
        String hash = passwordEncoder.encode(form.getPassword());
        member.setPassword(hash);

        // 회원 권한

        Authorities auth = new Authorities();
        auth.setMember(member);
        // 처음 가입시 일반 회원(USER)
        auth.setAuthority(Authority.USER);

    }

    /**
     * 회원정보 추가 또는 수정 처리
     */
    private void save(Member member, List<Authorities> authorities) {
        memberRepository.saveAndFlush(member);

        // 회원 권한 업데이트 처리 S
        if (authorities != null) {
            /**
             * 기존 권한을 삭제하고 다시 등록
             */
            QAuthorities _qAuthorities = QAuthorities.authorities;
            List<Authorities> items = (List<Authorities>) authoritiesRepository.findAll(_qAuthorities.member.eq(member));
            if (items != null) {
                authoritiesRepository.deleteAll(items);
            }

            authoritiesRepository.saveAllAndFlush(authorities);
        }

        // 회원 권한 업데이트 처리 E
    }
}
