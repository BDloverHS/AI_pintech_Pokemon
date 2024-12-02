package org.koreait.global.configs;

import org.koreait.member.services.LoginFailureHandler;
import org.koreait.member.services.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

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
        /* 인증 설정 E - 로그인 */

        return http.build(); // 설정 무력화
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
