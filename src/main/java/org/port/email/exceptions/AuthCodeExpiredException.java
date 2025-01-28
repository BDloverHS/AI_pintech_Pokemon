package org.port.email.exceptions;

import org.port.global.exceptions.BadRequestException;

public class AuthCodeExpiredException extends BadRequestException {
    public AuthCodeExpiredException() {
        super("Expired.authCode");
        setErrorCode(true);
    }
}
