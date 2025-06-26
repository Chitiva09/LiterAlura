package com.alura.literAlura.dto;


import com.alura.literAlura.entity.Author;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDto {

    private String name;
    private int birthyear;
    private int deathyear;
}
