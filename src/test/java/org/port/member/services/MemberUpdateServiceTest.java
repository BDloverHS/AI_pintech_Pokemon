package org.port.member.services;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.port.member.constants.Gender;
import org.port.member.controllers.RequestJoin;
import org.port.member.entities.Authorities;
import org.port.member.entities.Member;
import org.port.member.entities.QAuthorities;
import org.port.member.repositories.AuthoritiesRepository;
import org.port.member.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@SpringBootTest
@ActiveProfiles({"default","test"})
public class MemberUpdateServiceTest {

    @Autowired
    private MemberUpdateService updateService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    private RequestJoin form;

    @BeforeEach
    void init() {
        Faker faker = new Faker(Locale.KOREA);

        form = new RequestJoin();
        form.setEmail(faker.internet().emailAddress());
        form.setPassword("_aA123456");
        form.setName(faker.name().fullName());
        form.setBirthDt(LocalDate.now().minusYears(20L));
        form.setZipCode(faker.address().zipCode());
        form.setAddress(faker.address().fullAddress());
        form.setAddressSub(faker.address().buildingNumber());
        form.setNickName(faker.name().name());
        form.setGender(Gender.MALE);
        form.setRequiredTerms1(true);
        form.setRequiredTerms2(true);
        form.setRequiredTerms3(true);
        form.setOptionalTerms(List.of("advertisement"));
    }

    @Test
    @DisplayName("회원 가입 기능 테스트")
    void joinTest() {
        updateService.process(form);

        Member member = memberRepository.findByEmail(form.getEmail()).orElse(null);
        System.out.println(member);
        System.out.println(member.getAuthorities());
        System.out.println(form);
    }

    @Test
    void authoritiesTest() {
        updateService.process(form);

        Member member = memberRepository.findByEmail(form.getEmail()).orElse(null);
        System.out.println(member);

        QAuthorities qAuthorities = QAuthorities.authorities;
        List<Authorities> items = (List<Authorities>) authoritiesRepository.findAll(qAuthorities.member.eq(member));
    }
}
