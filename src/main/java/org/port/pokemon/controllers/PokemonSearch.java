package org.port.pokemon.controllers;

import lombok.Data;
import org.port.global.paging.CommonSearch;

import java.util.List;

@Data
public class PokemonSearch extends CommonSearch {
    private List<Long> seq;

    private List<String> searchTypes; // 검색할 타입 목록

    private Long sNum; // 시작 도감번호
    private Long eNum; // 끝 도감번호
}
