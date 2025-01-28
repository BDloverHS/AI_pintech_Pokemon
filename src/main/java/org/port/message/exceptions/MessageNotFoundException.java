package org.port.message.exceptions;

import org.port.global.exceptions.scripts.AlertBackException;
import org.springframework.http.HttpStatus;

public class MessageNotFoundException extends AlertBackException {
    public MessageNotFoundException() {
        super("NotFound.message", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
