package org.port.global.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 자동 스캔 범위 포함되는 대상
@Configuration
// 엔티티 변화 감지
// 엔티티의 생성/수정 시간 등을 자동으로 기록하도록 설정(사용할 때만 리스너가 돌아갈 수 있도록)
@EnableJpaAuditing
// @Scheduled 어노테이션을 사용해 작업 스케줄링(정기 실행)을 활성화하기 위함.
@EnableScheduling
// 세션 쪽을 redis에 저장시키기 위함
// 동일한 세션을 공유해 로그인을 유지시키기 위함.(세션에 저장함)
@EnableRedisHttpSession
//                                ┌> WebMvcWebMvc프레임워크의 주축이 되는 인터페이스
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
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/ ");
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
    // form의 양식에 hidden 값으로 넣어줘 PATCH, PUT, DELETE 같은 HTTP 메서드를 지원하기 위한 필터
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}