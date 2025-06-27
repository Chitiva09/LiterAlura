package com.alura.literAlura.service;

import java.io.IOException;
import java.util.List;

import com.alura.literAlura.entity.Author;
import com.alura.literAlura.entity.Book;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface BookService {

    // buscar por titulo
    Book findBookTitle(String title) throws JsonProcessingException;

    // mostrar libros registrados
    List<Book> showBooks();

    // mostrar autores registrados
    List<Author> showAuthors();

    // mostrar autores registrados en un determinado año
    void showAuthorsByYear(String year);

    // mostrar por idioma
    void showBooksByLanguage(String language);
}
