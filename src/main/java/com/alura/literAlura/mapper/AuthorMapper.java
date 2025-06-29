package com.alura.literAlura.mapper;

import com.alura.literAlura.dto.AuthorDto;
import com.alura.literAlura.entity.Author;
import com.alura.literAlura.entity.Book;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class AuthorMapper {

    public Author toEntity(AuthorDto dto) {

        Author author = new Author();
        author.setName(dto.getName());
        author.setBirthYear(dto.getBirthyear());
        author.setDeathYear(dto.getDeathyear());
        return author;
    }

    public AuthorDto toDto(Author author) {

        AuthorDto dto = new AuthorDto();
        dto.setName(author.getName());
        dto.setBirthyear(author.getBirthYear());
        dto.setDeathyear(author.getDeathYear());
        dto.setAuthorBooks(
                Optional.ofNullable(author.getBooks())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(Book::getTitle)
                        .collect(Collectors.toList()));

        return dto;
    }
}
