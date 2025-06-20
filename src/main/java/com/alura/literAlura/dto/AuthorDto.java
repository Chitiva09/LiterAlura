package com.alura.literAlura.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class AuthorDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("birth_year")
    private int birthyear;
    @JsonProperty("death_year")
    private int deathyear;

}
