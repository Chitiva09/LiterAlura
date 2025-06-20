package com.alura.literAlura.mapper;

import com.alura.literAlura.dto.AuthorDto;
import com.alura.literAlura.dto.BookDto;
import com.alura.literAlura.entity.Author;
import com.alura.literAlura.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {
 @Autowired
    private AuthorMapper authorMapper;

 public Book toEntity (BookDto dto) {
     Book book = new Book();
     book.setId(dto.getId());
     book.setTitle(dto.getTitle());
     book.setCategory(dto.getCategory());
     book.setLanguages(dto.getLanguages());
     book.setDescription(dto.getDescription());
     book.setImage(dto.getFormats().get("image/jpeg"));

     List<Author> authors = dto.getAuthors().stream()
             .map(authorMapper::toEntity)
             .collect(Collectors.toList());

     book.setAuthors(authors);
     return book;
 }

}
