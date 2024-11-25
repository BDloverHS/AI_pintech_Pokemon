package org.koreait.pokemon.api.entities;

import lombok.Data;

@Data
public class ApiResponse {
    private int count;
    private String next;
    private String previous;
}
