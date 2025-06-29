package com.alura.literAlura.service;

import java.util.List;
import java.util.stream.Collectors;

import com.alura.literAlura.client.Client;
import com.alura.literAlura.dto.AuthorDto;
import com.alura.literAlura.dto.BookDto;
import com.alura.literAlura.mapper.AuthorMapper;
import com.alura.literAlura.mapper.BookMapper;
import com.alura.literAlura.repository.AuthorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.alura.literAlura.entity.Author;
import com.alura.literAlura.entity.Book;
import com.alura.literAlura.repository.BookRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor//Esta anotación de lombok reemplaza la anotación @autowired
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final Client client;
    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;

    // buscar por titulo
    @Transactional
    @Override
    public BookDto findBookTitle(String title) throws JsonProcessingException {

        Book book = bookRepository.findByTitle(title);//reviso si hay un libro ya guardado en la base de datos con ese nombre

        if (book != null) {
            return bookMapper.toDto(book);
        }

        List<BookDto> apiGutendexBook = client.requestBook(title);// guardo en la variable la información obtenida desde el client

        if (apiGutendexBook.isEmpty()) {
            return null;
        }

        BookDto dto = apiGutendexBook.getFirst();//toma el primer resultado.
        System.out.println("DTO recibido: " + dto);
        Book newBook = bookMapper.toEntity(dto); //transforma el dto en una entidad Book

        if (newBook == null) {
            throw new IllegalArgumentException("Error: El objeto Book no se pudo crear desde el DTO");
        }
        List<Author> managedAuthors = newBook.getAuthors().stream()// Convierte la lista de autores en un Stream<Author> para procesarlos uno por uno de forma funcional
                .map(author -> authorRepository.findByName(author.getName())//Transforma cada author de la lista
                        // usando una función lambda. El objetivo es asegurarse de que cada author, si ya existe se reutiliza y si no existe se guarda
                        //authorRepository.findByName(author.getName()) Este metodo busca un autor por su nombre en la base de datos. devuelve un Optional<Author>
                        .orElseGet(() -> authorRepository.save(author)))//Aquí es donde usamos el Optional. si el autor existe,
                //lo retorna y si no existe ejecuta la funcion lambda, que guarda elautor y devuelve el nuevo objeto persistido
                .collect(Collectors.toList());//Convierte el Stream<Author> de vuelta en una lista. Es el resultado final:
        // la lista de autores ya gestionados, guardados o encontrados.


        newBook.setAuthors(managedAuthors);
        Book savedBook = bookRepository.save(newBook);// envia el libro optenido desde la api externa para guardarlo en la base de datos por medio del repository


        return bookMapper.toDto(savedBook);
    }

    // mostrar libros registrados
    @Transactional
    @Override
    public List<BookDto> showBooks() {
        List<Book> databaseQuery = bookRepository.findAll();

        if (databaseQuery.isEmpty()) {
            throw new EntityNotFoundException("No hay libros guardados");
        }

        return databaseQuery.stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    // mostrar autores registrados
    @Override
    public List<AuthorDto> showAuthors() {
        List<Author> databaseQuery = authorRepository.findAll();
        if (databaseQuery.isEmpty()) {
            throw new EntityNotFoundException("No hay autores registrados");
        }

        return databaseQuery.stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
    }

    // mostrar autores registrados en un determinado año
    @Override
    public List<AuthorDto> showAuthorsByYear(String year) {

        int tarjetYear = Integer.parseInt(year);

        List<Author> authors = authorRepository.findAll();

        if (authors.isEmpty()) {
            throw new EntityNotFoundException("No hay autores registrados en ese año");
        }

        List<AuthorDto> filteredAuthors = authors.stream()
                .map(authorMapper::toDto)
                .filter(listAuthors -> listAuthors.getBirthyear() <= tarjetYear && listAuthors.getDeathyear() >= tarjetYear)
                .collect(Collectors.toList());

        return filteredAuthors;
    }

    // mostrar libros por idioma
    @Transactional
    @Override
    public List<BookDto> showBooksByLanguages(String languages) {

        List<Book> searchedBooksLanguages = bookRepository.findByLanguagesContaining(languages);
    if (searchedBooksLanguages.isEmpty()) {
        throw new EntityNotFoundException("No hay libros registrados con ese lenguaje");
    }

        List<BookDto> filteredBookLanguages = searchedBooksLanguages.stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());

    return filteredBookLanguages;
    }

}