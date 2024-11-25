package org.koreait.pokemon;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static java.net.URI.create;

public class ApiTest {
    @Test
    void requestTest1() {
        String url = "https://pokeapi.co/api/v2/pokemon";
        RestTemplate tpl = new RestTemplate();
        String response = tpl.getForObject(URI.create(url), String.class);
    }
}
