package org.port.email.exceptions;

import org.port.global.exceptions.BadRequestException;

public class AuthCodeIssueException extends BadRequestException {
    public AuthCodeIssueException() {
        super("Fail.authCode.issue");
        setErrorCode(true);
    }
}
