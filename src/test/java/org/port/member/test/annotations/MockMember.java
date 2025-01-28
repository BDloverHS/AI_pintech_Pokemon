package org.port.member.test.annotations;

import org.port.member.constants.Authority;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 타겟 설정(메서드 위에서 사용할 수 있도록)
@Retention(RetentionPolicy.RUNTIME) // 적용 시점
@WithSecurityContext(factory = MockSecurityContextFactory.class) // MockSecurityContextFactory를 설정하기 위함.(가짜 사용자 생성)
public @interface MockMember {
    long seq() default 1L;
    String email() default "user01@test.org";
    String password() default "_aA123456";
    String name() default "사용자01";
    String nickName() default "닉네임01";
    Authority[] authority() default {Authority.USER};
}
