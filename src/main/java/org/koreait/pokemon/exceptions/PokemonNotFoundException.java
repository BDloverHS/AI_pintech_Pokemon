package org.koreait.pokemon.exceptions;

import org.koreait.global.exceptions.script.AlertBackException;
import org.springframework.http.HttpStatus;

public class PokemonNotFoundException extends AlertBackException {
    public PokemonNotFoundException() {
        super("NotFound.Pokemon", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
