package org.koreait.global.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * 모든 사용자 정의 예외 상위 클래스
 *
 */

@Getter @Setter
public class CommonException extends RuntimeException {
    private HttpStatus status;
    private boolean errorCode;

    public CommonException(String message, HttpStatus status) {
        this.status = status;
    }
}
