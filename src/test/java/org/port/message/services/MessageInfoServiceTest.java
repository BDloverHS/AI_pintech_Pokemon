package org.port.message.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.port.global.paging.ListData;
import org.port.member.constants.Gender;
import org.port.member.controllers.RequestJoin;
import org.port.member.entities.Member;
import org.port.member.repositories.MemberRepository;
import org.port.member.services.MemberUpdateService;
import org.port.message.controllers.MessageSearch;
import org.port.message.controllers.RequestMessage;
import org.port.message.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles({"default", "test"})
@Transactional
public class MessageInfoServiceTest {
    @Autowired
    private MessageInfoService infoService;

    @Autowired
    private MessageSendService sendService;

    @Autowired
    private MemberUpdateService updateService;

    @Autowired
    private MemberRepository memberRepository;

    private Member sender;
    private String receiver; // 이메일로 받음

    @BeforeEach
    void init() {
        for (int i = 1; i <= 2; i++) {
            RequestJoin form = new RequestJoin();
            form.setEmail("user0" + i + "@test.org");
            form.setName("이이름");
            form.setNickName("닉네임");
            form.setZipCode("00000");
            form.setAddress("주소");
            form.setAddressSub("나머지");
            form.setGender(Gender.MALE);
            form.setBirthDt(LocalDate.now());
            form.setPassword("_aA123456");
            form.setConfirmPassword(form.getPassword());
            updateService.process(form);
        }
    }

    @Test
    @WithUserDetails(value = "user01@test.org", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void listTest() {
        createMessages();

        MessageSearch search = new MessageSearch();
        search.setMode("send");
        ListData<Message> data = infoService.getList(search);
        List<Message> items = data.getItems();
        items.forEach(System.out::println);

        assertTrue(items.size() == 10);
    }

    void createMessages() {
        for (int i = 0; i < 10; i++) {
            RequestMessage message = new RequestMessage();
            message.setEmail("user02@test");
            message.setGid(UUID.randomUUID().toString());
            message.setSubject("제목" + i);
            message.setContent("내용" + i);
            sendService.process(message);
        }
    }
}
