package com.alura.literAlura.service;

import java.util.List;

import com.alura.literAlura.entity.Author;
import com.alura.literAlura.entity.Book;

public interface BookService {

    // buscar por titulo
    public Book findBookTitle(String title);

    // mostrar libros registrados
    public void showBooks(List<Book> books);

    // mostrar autores registrados
    public void showAuthors(List<Author> authors);

    // mostrar autores registrados en un determinado año
    public void showAuthorsByYear(String year);

    // mostrar por idioma
    public void showBooksByLanguage(String language);
}
