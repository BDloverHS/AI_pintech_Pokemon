package org.koreait.member.controllers;

import org.junit.jupiter.api.Test;
import org.koreait.member.entities.Member;
import org.koreait.member.services.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
// @ActiveProfiles({"default","test"})
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberInfoService infoService;


    @Test
    void infoMember() {
        Member user = infoService.get(1L);
        System.out.println(user);

        //List<Member> users = infoService.getList("testgid", null, null);
        //items.forEach(System.out::println);
    }
}
