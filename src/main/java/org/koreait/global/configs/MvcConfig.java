package org.koreait.global.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaAuditing
public class MvcConfig implements WebMvcConfigurer {
    /**
     * 정적 경로 설정(CSS, JS, 이미지 자원 등)
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // resources 폴더 안에 있는 static 폴더를 정적 경로 설정함
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/ ");
    }
}