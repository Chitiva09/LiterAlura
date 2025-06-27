package com.alura.literAlura.dto;


import com.alura.literAlura.entity.Author;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDto {

    private String name;
    @JsonProperty("birth_year")
    private int birthyear;
    @JsonProperty("death_year")
    private int deathyear;
}
