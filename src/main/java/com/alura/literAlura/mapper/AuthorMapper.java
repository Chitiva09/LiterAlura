package com.alura.literAlura.mapper;

import com.alura.literAlura.dto.BookDto;
import com.alura.literAlura.entity.Author;
import org.springframework.stereotype.Component;


@Component
public class AuthorMapper {

    public Author toEntity (BookDto dto) {

        Author author = new Author();
        author.setName(dto.getName());
        author.setBirthYear(dto.getBirthyear());
        author.setDeathYear(dto.getDeathyear());
        return author;
    }

}
