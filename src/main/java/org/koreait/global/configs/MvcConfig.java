package org.koreait.global.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 자동 스캔 범위 포함
@Configuration
// 엔티티 변화 감지
@EnableJpaAuditing
// @Scheduled을 활성화시키기 위함
@EnableScheduling
// 세션 쪽을 redis에 저장시키기 위함
// 서버가 달라도 로그인을 유지시키기 위해서는 세션에 저장시켜야 하기 때문임
@EnableRedisHttpSession
public class MvcConfig implements WebMvcConfigurer {
    /**
     * 정적 경로 설정(CSS, JS, 이미지 자원 등)
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // resources 폴더 안에 있는 static 폴더를 정적 경로 설정함
        // classpath : 클래스 파일을 인식할 수 있는 경로
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/ ");
    }

    /**
     * PATCH, PUT, DELETE 등등
     * PATCH 메서드로 요청을 보내는 경우
     * <form method='POST' ...>
     *      <input type='hidden' name='_method' value='PATCH'>
     * </form>
     * @return
     */
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}