package org.koreait.member.services;

import org.junit.jupiter.api.Test;
import org.koreait.member.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
// @AutoConfigureMockMvc
public class MemberDeleteServiceTest {

    @Autowired
    private MemberInfoService infoService;

    @Autowired
    private MemberDeleteService deleteService;

    @Test
    void test1() {
        Member user = infoService.get(7L);
        System.out.println(user);
    }

    @Test
    void test2() {
        Member user = deleteService.delete(7L);
        System.out.println(user);
    }
}
