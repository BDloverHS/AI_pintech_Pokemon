package org.port.board.exceptions;

import org.port.global.exceptions.scripts.AlertBackException;
import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends AlertBackException {
    public CommentNotFoundException() {
        super("NotFound", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
