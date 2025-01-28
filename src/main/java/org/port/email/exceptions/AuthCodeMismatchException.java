package org.port.email.exceptions;

import org.port.global.exceptions.BadRequestException;

public class AuthCodeMismatchException extends BadRequestException {
    public AuthCodeMismatchException() {
        super("Mismatch.authCode");
        setErrorCode(true);
    }
}
