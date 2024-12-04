package org.koreait.global.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

import java.util.Map;
import java.util.Objects;

/**
 * 모든 사용자 정의 예외 상위 클래스
 *
 */

@Getter @Setter
public class CommonException extends RuntimeException {
    private HttpStatus status;
    private boolean errorCode;
    private Map<String, Object> errorMessages;

    public CommonException(String message, HttpStatus status) {
        super(message);
        this.status = Objects.requireNonNullElse(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public CommonException(Map<String, Object> errorMessages, HttpStatus status) {
        this.errorMessages = errorMessages;
        this.status = status;
    }
}
