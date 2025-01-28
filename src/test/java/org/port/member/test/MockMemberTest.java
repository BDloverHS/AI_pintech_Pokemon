package org.port.member.test;

import org.junit.jupiter.api.Test;
import org.port.member.constants.Authority;
import org.port.member.entities.Member;
import org.port.member.libs.MemberUtil;
import org.port.member.test.annotations.MockMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"default","test"})
public class MockMemberTest {
    @Autowired
    private MemberUtil memberUtil;

    @Test
    @MockMember(authority = {Authority.USER, Authority.MANAGER}) // 관리자 권한을 위해 권한을 바꿈(디폴트를 USER로 설정한 상황임)
    void test1() {
        Member member = memberUtil.getMember();
        System.out.println(member);
        System.out.println(memberUtil.isLogin()); // 제대로 가짜 로그인이 되었는지 확인하기 위함
        System.out.println(memberUtil.isAdmin()); // USER일 땐 false, MANAGER, ADMIN일 시 true
    }
}
