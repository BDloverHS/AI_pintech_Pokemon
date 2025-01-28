package org.port.pokemon.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // json이 무시함
public class Genus {
    private String genus;
    private UrlItem language;
}
