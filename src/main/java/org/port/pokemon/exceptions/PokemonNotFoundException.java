package org.port.pokemon.exceptions;

import org.port.global.exceptions.scripts.AlertBackException;
import org.springframework.http.HttpStatus;

public class PokemonNotFoundException extends AlertBackException {
    public PokemonNotFoundException() {
        super("NotFound.Pokemon", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
