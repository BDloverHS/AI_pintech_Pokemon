package org.koreait.pokemon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.koreait.pokemon.api.entities.ApiPokemon;
import org.koreait.pokemon.api.entities.ApiResponse;
import org.koreait.pokemon.api.services.ApiUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootTest
public class ApiTest2 {

    @Autowired
    private ApiUpdateService service;

    @Test
    void updateTest1() {
        service.update(1);
    }
}