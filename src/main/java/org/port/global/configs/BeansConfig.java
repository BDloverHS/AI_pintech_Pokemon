// 공용으로 쓸 건데 수동으로 관리할 객체들
package org.port.global.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeansConfig {
    @Lazy
    @Bean
    // 원격에 요청 및 응답을 주고받기 위한 스프링 제공 편의 기능
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Lazy
    @Bean
    // 객체 간의 매핑을 자동으로 처리해주는 라이브러리
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        return new ModelMapper();
    }

    @Lazy
    @Bean
    // 자바 객체를 제이슨 문자열로 대체
    public ObjectMapper objectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule()); // java8 data & time api - java.time 패키지

        return om;
    }
}
