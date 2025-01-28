package org.port.bestlist.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.port.bestlist.services.BestService;
import org.port.pokemon.entities.Pokemon;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/best")
public class ApiBestController {

    private final HttpServletRequest request;
    private final BestService service;

    @GetMapping({"/add", "/remove"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void process(@RequestParam("seq") Long seq, @RequestParam("pokemon")Pokemon pokemon) {
        String mode = request.getRequestURI().contains("/remove") ? "remove" : "add";

        service.process(mode, seq, pokemon);
    }
}
