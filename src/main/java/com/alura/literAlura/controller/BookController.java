package com.alura.literAlura.controller;


import com.alura.literAlura.entity.Book;
import com.alura.literAlura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService service;

    @GetMapping(value = "/libros/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Book> findBookTitle(@PathVariable("title") String title){

        Book books = service.findBookTitle( title);
        return new ResponseEntity<Book>(service.findBookTitle(title), HttpStatus.OK);
    }


}
