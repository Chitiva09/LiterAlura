package com.alura.literAlura.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.alura.literAlura.client.Client;
import com.alura.literAlura.dto.BookDto;
import com.alura.literAlura.dto.GutendexResponse;
import com.alura.literAlura.mapper.BookMapper;
import com.alura.literAlura.repository.AuthorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.literAlura.entity.Author;
import com.alura.literAlura.entity.Book;
import com.alura.literAlura.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    Client client;

    @Autowired
    BookMapper bookMapper;

    // buscar por titulo
    @Override
    public Book findBookTitle(String title) throws JsonProcessingException {

        Book book = bookRepository.findByTitle(title);//reviso si hay un libro ya guardado en la base de datos con ese nombre

        if (book !=null){
            return book;
        }

        List<BookDto> apiGutendexBook = client.requestBook(title);// guardo en la variable la información obtenida desde el client

        if (apiGutendexBook.isEmpty()){
            return null;
        }

        BookDto dto = apiGutendexBook.get(0);//toma el primer resultado.
        System.out.println("DTO recibido: " + dto);
        Book newBook = bookMapper.toEntity(dto); //transforma el dto en una entidad Book
        System.out.println("Book mapeado: " + book);
        if (newBook == null){
            throw new IllegalArgumentException("Error: El objeto Book no se pudo crear desde el DTO");
        }
        List<Author> managedAuthors = newBook.getAuthors().stream()// Convierte la lista de autores en un Stream<Author> para procesarlos uno por uno de forma funcional
                                                        .map(author -> authorRepository.findByName(author.getName())//Transforma cada author de la lista
                                                                // usando una función lambda. El objetivo es asegurarse de que cada author, si ya existe se reutiliza y si no existe se guarda
                                                                //authorRepository.findByName(author.getName()) Este metodo busca un autor por su nombre en la base de datos. devuelve un Optional<Author>
                                                                .orElseGet(()-> authorRepository.save(author)))//Aquí es donde usamos el Optional. si el autor existe,
                                                                //lo retorna y si no existe ejecuta la funcion lambda, que guarda elautor y devuelve el nuevo objeto persistido
                                                        .collect(Collectors.toList());//Convierte el Stream<Author> de vuelta en una lista. Es el resultado final:
                                                                // la lista de autores ya gestionados, guardados o encontrados.



        newBook.setAuthors(managedAuthors);
        bookRepository.save(newBook);// envia el libro optenido desde la api externa para guardarlo en la base de datos por medio del repository


        return newBook;
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