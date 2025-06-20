package com.alura.literAlura.mapper;

import com.alura.literAlura.dto.AuthorDto;
import com.alura.literAlura.entity.Author;

public class AuthorMapper {

    public Author toEntity (AuthorDto dto) {

        Author author = new Author();
        author.setName(dto.getName());
        author.setBirthYear(dto.getBirthyear());
        author.setDeathYear(dto.getDeathyear());
        return author;
    }

}
