package org.port.pokemon.services;

import org.junit.jupiter.api.Test;
import org.port.global.paging.ListData;
import org.port.pokemon.controllers.PokemonSearch;
import org.port.pokemon.entities.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PokemonInfoServiceTest {
    @Autowired
    private PokemonInfoService infoService;

    @Test
    void test1() {
        //Pokemon item = infoService.get(1L);
        //System.out.println(item);

        PokemonSearch search = new PokemonSearch();
        search.setSkey("나무줄기");
        ListData<Pokemon> items = infoService.getList(search);
        items.getItems().forEach(System.out::println);
    }

    /*
    @Test
    void test2() {
        List<String> items = infoService.allTypes();
        System.out.println(items);
    }
     */
}
