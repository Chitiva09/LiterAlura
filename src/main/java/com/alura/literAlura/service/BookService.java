package com.alura.literAlura.service;

import java.util.List;

import com.alura.literAlura.dto.AuthorDto;
import com.alura.literAlura.dto.BookDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface BookService {

    // buscar por titulo
    BookDto findBookTitle(String title) throws JsonProcessingException;

    // mostrar libros registrados
    List<BookDto> showBooks();

    // mostrar autores registrados
    List<AuthorDto> showAuthors();

    // mostrar autores vivos en un determinado año
    List<AuthorDto> showAuthorsByYear(String year);

    // mostrar por idioma
    List<BookDto> showBooksByLanguages(String languages);
}
