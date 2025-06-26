package com.alura.literAlura.controller;


import com.alura.literAlura.dto.BookDto;
import com.alura.literAlura.entity.Author;
import com.alura.literAlura.entity.Book;
import com.alura.literAlura.mapper.AuthorMapper;
import com.alura.literAlura.mapper.BookMapper;
import com.alura.literAlura.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired
    BookService service;

    @Autowired
    AuthorMapper authorMapper;

    @Autowired
    BookMapper bookMapper;

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> findBookTitle(@RequestParam("search") String title) throws IOException, InterruptedException {

        Book books = service.findBookTitle(title);

        if (books == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping(value = "/books/showBooks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> showBooks() {

        try {
            List<Book> books = service.showBooks();
            List<BookDto> booksDto = books.stream()
                    .map(bookMapper::toDto)
                    .collect(Collectors.toList());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Total de libros", String.valueOf(books.size()));
            return new ResponseEntity<>(books, headers, HttpStatus.OK);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la base de datos");
        }


    }

}
