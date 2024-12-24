package org.koreait.pokemon.type;

import com.querydsl.core.BooleanBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;
import org.koreait.pokemon.controllers.PokemonSearch;
import org.koreait.pokemon.entities.QPokemon;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class TypeFilter {
    private List<String> stype;
    private String baseUrl;

    public TypeFilter(@SessionAttribute("search")PokemonSearch search, HttpServletRequest request) {

        QPokemon pokemon = QPokemon.pokemon;



        /* 타입 검색 S */
        BooleanBuilder typeBuilder = new BooleanBuilder();
        stype = search.getStype(); // 타입 리스트

        if (stype != null && !stype.isEmpty()) {
            for (String type : stype) {
                if (StringUtils.hasText(type)) {
                    // 타입이 ||로 구분되어 저장되어 있으므로 contains를 사용
                    typeBuilder.or(pokemon.types.contains(type));
                }
            }
        }
        /* 타입 검색 E */

        /* 쿼리스트링 값 처리 S */
        String qs = request.getQueryString();
        baseUrl = "?";
        if (StringUtils.hasText(qs)) {
            baseUrl += Arrays.stream(qs.split("&"))
                    .filter(s -> !s.contains("type="))
                    .collect(Collectors.joining("&")) + "&";
        }
        baseUrl += "type=";
        /* 쿼리스트링 값 처리 E */
    }
}
