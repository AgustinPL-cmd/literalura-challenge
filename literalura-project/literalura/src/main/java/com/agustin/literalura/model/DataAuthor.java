package com.agustin.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataAuthor(String name,
                         @JsonAlias("birth_year") int birthYear,
                         @JsonAlias("death_year") int deathYear) {

}
