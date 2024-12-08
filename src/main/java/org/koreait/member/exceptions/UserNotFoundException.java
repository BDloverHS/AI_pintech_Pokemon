package org.koreait.member.exceptions;

import org.koreait.global.exceptions.script.AlertBackException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AlertBackException {
    public UserNotFoundException(String message) {
        super("NotFound.user", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
