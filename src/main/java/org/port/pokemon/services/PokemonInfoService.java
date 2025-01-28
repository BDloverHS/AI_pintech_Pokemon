package org.port.pokemon.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.port.global.libs.Utils;
import org.port.global.paging.ListData;
import org.port.global.paging.Pagination;
import org.port.pokemon.controllers.PokemonSearch;
import org.port.pokemon.entities.Pokemon;
import org.port.pokemon.entities.QPokemon;
import org.port.pokemon.exceptions.PokemonNotFoundException;
import org.port.pokemon.repositories.PokemonRepository;
import org.port.wishlist.constants.WishType;
import org.port.wishlist.services.WishService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.domain.Sort.Order.asc;

@Lazy
@Service
@RequiredArgsConstructor
public class PokemonInfoService {

    private final PokemonRepository pokemonRepository;
    private final HttpServletRequest request;
    private final Utils utils;
    private final JPAQueryFactory queryFactory;
    private final WishService wishService;
    private final RestTemplate tpl;


    /**
     * 포켓몬 목록 조회
     *
     * @param search
     * @return
     */
    public ListData<Pokemon> getList(PokemonSearch search) {
        int page = Math.max(search.getPage(), 1); // 페이지 번호
        int limit = search.getLimit(); // 한페이지 당 레코드 갯수
        limit = limit < 1 ? 18 : limit;
        QPokemon pokemon = QPokemon.pokemon;

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(asc("seq")));

        /* 검색 처리 S */

        BooleanBuilder andBuilder = new BooleanBuilder();

        // 타입 필터 S
        List<String> searchTypes = search.getSearchTypes();

        if (searchTypes != null && !searchTypes.isEmpty()) {
            // searchTypes.forEach(type -> andBuilder.or(pokemon.types.contains(type)));
            BooleanBuilder orBuilder = new BooleanBuilder();
            searchTypes.forEach(type -> orBuilder.or(pokemon.types.contains(type)));
            andBuilder.and(orBuilder);
        }
        // 타입 필터 E

        // 도감번호 필터 S
        /*
        Long sNum = search.getSNum() != null ? search.getSNum() : 1L;
        Long eNum = search.getENum() != null ? search.getENum() : pokemonRepository.count();

        andBuilder.and(pokemon.seq.between(sNum, eNum));
        */

        // 값이 하나만 들어와도 검색
        Long sNum = search.getSNum();
        Long eNum = search.getENum();
        if (sNum != null) andBuilder.and(pokemon.seq.goe(sNum));
        if (eNum != null) andBuilder.and(pokemon.seq.loe(eNum));

        // 도감번호 필터 E

        // 키워드 검색 S
        String sopt = search.getSopt();
        String skey = search.getSkey();
        if (StringUtils.hasText(skey)) { // 키워드 검색
            skey = skey.trim();
            if (sopt.equals("NAME")) {
                andBuilder.and(pokemon.name.contains(skey));
            } else if (sopt.equals("FLAVOR")) {
                andBuilder.and(pokemon.flavorText.contains(skey));
            } else {
                andBuilder.and(pokemon.name
                        .concat(pokemon.flavorText)
                        .contains(skey));
            }
        }

        List<Long> seq = search.getSeq();
        if (seq != null && !seq.isEmpty()) {
            andBuilder.and(pokemon.seq.in(seq));
        }
        // 키워드 검색 E

        /* 검색 처리 E */

        Page<Pokemon> data = pokemonRepository.findAll(andBuilder, pageable);
        List<Pokemon> items = data.getContent(); // 조회된 목록

        // 추가 정보 처리
        items.forEach(this::addInfo);

        int ranges = utils.isMobile() ? 5 : 10;
        Pagination pagination = new Pagination(page, (int)data.getTotalElements(), ranges, limit, request);

        return new ListData<>(items, pagination);
    }

    // 찜한 포켓몬 리스트
    public ListData<Pokemon> getMyPokemons(PokemonSearch search) {
        List<Long> seq = wishService.getMyWish(WishType.POKEMON);
        if (seq == null || seq.isEmpty()) {
            return new ListData<>();
        }

        search.setSeq(seq);

        return getList(search);
    }

    /**
     * 포켓몬 단일 조회
     *
     * @param seq
     * @return
     */
    public Pokemon get(Long seq) {

        Pokemon item = pokemonRepository.findById(seq).orElseThrow(PokemonNotFoundException::new);

        // 추가 정보 처리
        addInfo(item, true);

        return item;
    }

    /**
     * 추가 정보 처리
     *
     * @param item
     */
    private void addInfo(Pokemon item) {
        // abilities
        String abilities = item.getAbilities();
        if (StringUtils.hasText(abilities)) {
            item.set_abilities(Arrays.stream(abilities.split("\\|\\|")).toList());
        }

        // types
        String types = item.getTypes();
        if (StringUtils.hasText(types)) {
            item.set_types(Arrays.stream(types.split("\\|\\|")).toList());
        }
    }

    private void addInfo(Pokemon item, boolean isView) {
        addInfo(item);

        if (!isView) return;

        long seq = item.getSeq();
        long lastSeq = getLastSeq();

        // 이전 포켓몬 정보 - preItem
        long prevSeq = seq - 1L;
        prevSeq = prevSeq < 1L ? lastSeq : prevSeq;


        // 다음 포켓몬 정보 - nextItem
        long nextSeq = seq + 1L;
        nextSeq = nextSeq > lastSeq ? 1L : nextSeq;

        QPokemon pokemon = QPokemon.pokemon;
        List<Pokemon> items = (List<Pokemon>)pokemonRepository.findAll(pokemon.seq.in(prevSeq, nextSeq));

        Map<String, Object> prevItem = new HashMap<>();
        Map<String, Object> nextItem = new HashMap<>();
        for (int i = 0; i < items.size(); i++) {
            Pokemon _item = items.get(i);

            Map<String, Object> data = _item.getSeq().longValue() == prevSeq ? prevItem : nextItem;
            data.put("seq", _item.getSeq());
            data.put("name", _item.getName());
            data.put("nameEn", _item.getNameEn());
        }

        item.setPrevItem(prevItem);
        item.setNextItem(nextItem);
    }

    private Long getLastSeq() {
        QPokemon pokemon = QPokemon.pokemon;

        return queryFactory.select(pokemon.seq.max()).from(pokemon).fetchFirst();
    }
}