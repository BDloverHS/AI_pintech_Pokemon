package org.koreait.pokemon;

import org.junit.jupiter.api.Test;
import org.koreait.pokemon.api.services.ApiUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApiTest2 {

    @Autowired
    private ApiUpdateService service;

    @Test
    void updateTest1() {
        for (int i = 1; i <= 5; i++) {
            service.update(i);
        }
    }
}