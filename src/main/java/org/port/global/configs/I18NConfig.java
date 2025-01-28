package org.port.global.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

// Interationalization : 국제
@Configuration
public class I18NConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
                // .addPathPatterns("/**") // 사이트 전역에 적용하는 패턴일 경우 생략 가능
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("language"); // 한국어 : ko, 영어 : en, 일본어 : jp 등

        return interceptor;
    }

    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieMaxAge(60 * 60); // 60(초) * 60
        resolver.setCookieName("language");

        return resolver;
    }
}

// http://localhost:3000/member/login