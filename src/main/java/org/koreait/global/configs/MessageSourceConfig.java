package org.koreait.global.configs;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.addBasenames("messages.commons", "messages.validations", "messages.errors", "messages.footer", "messages.pokemon");
        ms.setDefaultEncoding("UTF-8");
        // 코드형태가 출력됨. 예) #{메시지_코드} 일 때, 메시지_코드라는 키의 값이 없을 경우 키를 출력하겠다고 설정한 것.
        ms.setUseCodeAsDefaultMessage(true);

        return ms;
    }
}
