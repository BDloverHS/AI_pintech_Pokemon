package org.koreait.global.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Swagger : API 명세( API 가이드 )
@OpenAPIDefinition(info=@Info(title="포켓몬 도감 API", description="/api/file - 파일 API"))
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi openGroup() {
        return GroupedOpenApi.builder()
                .group("포켓몬 도감 API")
                // /api로 시작하는 링크는 문서화하기 위함
                .pathsToMatch("/api/**")
                .build();
    }
}
