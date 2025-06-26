package com.alura.literAlura.mapper;

import com.alura.literAlura.dto.AuthorDto;
import com.alura.literAlura.dto.BookDto;
import com.alura.literAlura.entity.Author;
import com.alura.literAlura.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    @Autowired
    private AuthorMapper authorMapper;

    public Book toEntity(BookDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setCategory(String.join(", ", dto.getCategory()));//Acá estoy convirtiendo un list que recibo del Dto a un String que necesito en el entity
        book.setLanguages(String.join(", ", dto.getLanguages()));
        book.setDescription(dto.getDescription().isEmpty() ? "" : dto.getDescription().get(0));

        if (dto.getFormats() != null && dto.getFormats().containsKey("image/jpeg")) {
            book.setImage(dto.getFormats().get("image/jpeg"));
        }

        List<Author> author = dto.getAuthors().stream()
                .map(authorMapper::toEntity)
                .collect(Collectors.toList());
        book.setAuthors(author);

        return book;
    }

    public BookDto toDto(Book book) {
        BookDto dto = new BookDto();
        dto.setTitle(book.getTitle());
        dto.setCategory(List.of(book.getCategory().split(",\\s*")));
        dto.setLanguages(List.of(book.getLanguages().split(",\\s*")));


        dto.setImage(book.getImage());
        dto.setDescription(Collections.singletonList(book.getDescription()));

        List<AuthorDto> authorDtos = book.getAuthors().stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
        dto.setAuthors(authorDtos);

        return dto;
    }
}
