package org.koreait.global.configs;

import org.koreait.member.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 스프링 시큐리티 설정
 *
 */
@Configuration
// 특정 메서드만 통제하려고 할 때 사용하는 어노테이션
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private MemberInfoService memberInfoService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        /* 인증 설정 S - 로그인, 로그아웃 */
        http.formLogin(c -> {
           c.loginPage("/member/login") // 로그인 양식을 처리할 수 있는 주소
                   .usernameParameter("email")
                   .passwordParameter("password")
                   .failureHandler(new LoginFailureHandler())
                   .successHandler(new LoginSuccessHandler());
        });

        http.logout(c -> {
           c.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")).logoutSuccessUrl("/member/login");
        });
        /* 인증 설정 E - 로그인, 로그아웃 */

        /* 인가 설정 S - 페이지 접근 통제 */
        /**
         * authenticated() : 인증받은 사용자만 접근
         * anonymous() : 인증받지 않은 사용자만 접근
         * permitAll() : 모든 사용자가 접근 가능
         * hasAuthority("권한 명칭") : 하나의 권한을 체크
         * hasAnyAuthority("권한1", "권한2", "권한3", ...) : 나열된 권한 중 하나라도 충족하면 접근 가능
         * ROLE
         * ROLE_명칭
         * hasROLE("명칭")
         * hasAnyRole(...)
         */

        http.authorizeHttpRequests(c -> {
            c.requestMatchers("/mypage/**").authenticated() // 인증한 회원
                    .requestMatchers("/member/login", "/member/join", "/member/agree").anonymous() // 미인증 회원
                    .requestMatchers("/admin/**").hasAnyAuthority("MANAGER", "ADMIN") // 관리자 페이지는 MANAGER, ADMIN 권한
                    .anyRequest().permitAll(); // 나머지 페이지는 모두 접근 가능
        });


        http.exceptionHandling(c -> {
           c.authenticationEntryPoint(new MemberAuthenticationExceptionHandler()) // 미로그인시 인가 실패
                   .accessDeniedHandler(new MemberAccessDeniedHandler()); // 로그인 이후 인가 실패
        });


        /* 인가 설정 E - 페이지 접근 통제 */

        /* 자동 로그인 설정 S */
        http.rememberMe(c -> {
            c.rememberMeParameter("autoLogin")
                    .tokenValiditySeconds(60 * 60 * 24 * 30) // 자동 로그인을 유지할 시간(디폴트 값 : 14일, 이 코드에선 한달)
                    .userDetailsService(memberInfoService)
                    .authenticationSuccessHandler(new LoginSuccessHandler());
        });
        /* 자동 로그인 설정 E */

        // 빌드를 만들어서 반환
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
