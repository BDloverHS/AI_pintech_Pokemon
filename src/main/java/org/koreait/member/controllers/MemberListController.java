package org.koreait.member.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.koreait.global.libs.Utils;
import org.koreait.global.rests.JSONData;
import org.koreait.member.entities.Member;
import org.koreait.member.services.MemberDeleteService;
import org.koreait.member.services.MemberInfoService;
import org.koreait.member.services.MemberUpdateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Member", description = "유저 목록, 조회, 수정, 삭제 기능 제공합니다.")
@RestController
@RequestMapping("/member/list")
@RequiredArgsConstructor
public class MemberListController {
    private final Utils utils;
    private final MemberInfoService infoService;
    private final MemberDeleteService deleteService;
    private final MemberUpdateService updateService;

    // 유저 조회 S
    @GetMapping("/info/{seq}")
    public JSONData info(@PathVariable("seq") Long seq) {

        Member user = infoService.get(seq);

        return new JSONData(user);
    }
    // 유저 조회 E

}
