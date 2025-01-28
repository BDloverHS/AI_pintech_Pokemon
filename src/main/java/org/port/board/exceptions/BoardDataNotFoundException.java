package org.port.board.exceptions;

import org.port.global.exceptions.scripts.AlertBackException;
import org.springframework.http.HttpStatus;

public class BoardDataNotFoundException extends AlertBackException {
    public BoardDataNotFoundException() {
        super("NotFound.boardData", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
