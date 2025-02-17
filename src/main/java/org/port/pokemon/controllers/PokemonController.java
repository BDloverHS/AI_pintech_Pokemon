package org.port.pokemon.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.port.global.annotations.ApplyErrorPage;
import org.port.global.libs.Utils;
import org.port.global.paging.ListData;
import org.port.pokemon.constants.Types;
import org.port.pokemon.entities.Pokemon;
import org.port.pokemon.services.PokemonInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@ApplyErrorPage
@RequestMapping("/pokemon")
@RequiredArgsConstructor
public class PokemonController {

    private final Utils utils;
    private final HttpServletRequest request;
    private final PokemonInfoService infoService;

    @GetMapping("/list")
    public String list(@RequestParam(required=false, name="searchTypes") List<String> searchTypes, @RequestParam(name="refresh", required = false) boolean refresh, @ModelAttribute PokemonSearch search, Model model) {
        commonProcess("list", model);
        if (refresh) {
            search = new PokemonSearch();
        }
        ListData<Pokemon> data = infoService.getList(search);
        List<String> types = Arrays.stream(Types.values()).map(type -> type.name().toLowerCase()).collect(Collectors.toList());

        List<String> selectedTypes = (searchTypes != null) ? searchTypes : List.of();

        model.addAttribute("items", data.getItems());
        model.addAttribute("pagination", data.getPagination());
        model.addAttribute("types", types);

        model.addAttribute("selectedTypes", selectedTypes);

        return utils.tpl("pokemon/list");
    }

    @GetMapping("/view/{seq}")
    public String view(@PathVariable("seq") Long seq, Model model) {
        Pokemon item = infoService.get(seq);
        model.addAttribute("item", item);

        commonProcess("view", model);
        return utils.tpl("pokemon/view");
    }

    private void commonProcess(String mode, Model model) {
        mode = StringUtils.hasText(mode) ? mode : "list";
        String pageTitle = utils.getMessage("포켓몬_도감");

        List<String> addCss = new ArrayList<>();
        List<String> addCommonScript = new ArrayList<>();
        List<String> addScript = new ArrayList<>();

        addCss.add("pokemon/style"); // 포켓몬 도감 페이지 공통 스타일(목록, 상세)
        addCommonScript.add("wish"); // 찜하기

        if (mode.equals("list")) {
            addCss.add("pokemon/list"); // 목록쪽에만 적용되는 스타일
            addScript.add("pokemon/list");
        } else if (mode.equals("view")) {
            addCss.add("pokemon/view"); // 상세쪽에만 적용되는 스타일

            // 상세 보기에서는 포켓몬 이름으로 제목을 완성
            Pokemon item = (Pokemon) model.getAttribute("item");
            if (item != null) {
                pageTitle = String.format("%s - %s", item.getName(), pageTitle);
            }
        }

        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("addCss", addCss);
        model.addAttribute("addScript", addScript);
        model.addAttribute("addCommonScript", addCommonScript);
    }
}
