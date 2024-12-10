package org.koreait.pokemon.controllers;

import lombok.RequiredArgsConstructor;
import org.koreait.global.annotations.ApplyErrorPage;
import org.koreait.global.libs.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ApplyErrorPage
@RequestMapping("/pokemon")
@RequiredArgsConstructor
public class PokemonController {

    private final Utils utils;

    @GetMapping("/list")
    public String list() {
        return utils.tpl("pokemon/list");
    }

    @GetMapping("/view/{seq}")
    public String view(@PathVariable("seq") Long seq) {
        return utils.tpl("pokemon/view");
    }


}
