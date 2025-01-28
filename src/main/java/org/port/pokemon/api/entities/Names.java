package org.port.pokemon.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Names {
    private UrlItem language;
    private String name;
}
