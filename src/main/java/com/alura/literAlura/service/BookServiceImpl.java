package com.alura.literAlura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.literAlura.entity.Author;
import com.alura.literAlura.entity.Book;
import com.alura.literAlura.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    // buscar por titulo
    @Override
    public void findBookTitle(String title) {


    }

    // mostrar libros registrados
    @Override
    public void showBooks(List<Book> books) {
    }

    // mostrar autores registrados
    @Override
    public void showAuthors(List<Author> authors) {
    }

    // mostrar autores registrados en un determinado año
    @Override
    public void showAuthorsByYear(String year) {
    }

    // mostrar por idioma
    @Override
    public void showBooksByLanguage(String language) {
    }

}