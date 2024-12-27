package org.koreait.pokemon.controllers;

import lombok.Data;
import org.koreait.global.paging.CommonSearch;

import java.util.List;

@Data
public class PokemonSearch extends CommonSearch {
    private List<Long> seq;
    private String skey; // 검색 키워드
    private List<String> types;
}
