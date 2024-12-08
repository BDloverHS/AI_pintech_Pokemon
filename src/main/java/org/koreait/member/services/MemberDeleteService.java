package org.koreait.member.services;

import lombok.RequiredArgsConstructor;
import org.koreait.member.entities.Member;
import org.koreait.member.libs.MemberUtil;
import org.koreait.member.repositories.MemberRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
@RequiredArgsConstructor
public class MemberDeleteService {
    private final MemberInfoService infoService;
    private final MemberRepository repository;
    private final MemberUtil memberUtil;

    public Member delete(Long seq) {
        Member user = infoService.get(seq);
        repository.delete(user);
        repository.flush();

        return user;
    }
}
