package org.koreait.global.libs;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Utils {

    private final HttpServletRequest request;

    public boolean isMobile() {
        // 요청 헤더 쪽의 User-Agent에서 브라우저 정보를 가져옴
        String ua = request.getHeader("User-Agent");
        String pattern = ".*(iPhone|iPod|iPad|BlackBerry|Android|Windows CE|LG|MOT|SAMSUNG|SonyEricsson).*";

        return ua.matches(pattern);
    }

    /**
     * mobile, front 템플릿 분리 함수
     *
     * @param path
     * @return
     */

    public String tpl(String path) {
        String prefix = isMobile() ? "mobile" : "front";

        return String.format("%s/%s", prefix, path);
    }
}