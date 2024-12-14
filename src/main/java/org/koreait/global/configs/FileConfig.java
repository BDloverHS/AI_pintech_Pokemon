package org.koreait.global.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 설정 클래스이기 때문에 넣음
@Configuration
@RequiredArgsConstructor
// @ConfigurationProperties로 정의된 클래스를 스프링 컨테이너에 등록하여 사용할 수 있게 함
// (FileProperties 클래스를 설정 프로퍼티 클래스로 활성화)
@EnableConfigurationProperties(FileProperties.class)
public class FileConfig implements WebMvcConfigurer {
    private final FileProperties properties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(properties.getUrl() + "**").addResourceLocations("file:///" + properties.getPath());

    }
}
