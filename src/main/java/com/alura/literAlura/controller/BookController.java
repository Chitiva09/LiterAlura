package com.alura.literAlura.controller;


import com.alura.literAlura.dto.AuthorDto;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
//Esta clase contiene los endpoint que se utilizaran para la comunicación entre la api y el cliente.
@RestController
public class BookController {

    @Autowired
    BookService service;

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)// El value indica la asignación que se le dara a este endpoint en el url que usa el cliente
    public ResponseEntity<BookDto> findBookTitle(@RequestParam("search") String title) throws IOException{//Al indicar que es un @RequestParam quiere decir que se utilizara esa asignacion para la busqueda

        BookDto books = service.findBookTitle(title); //Se envía el título recibido desde la url por medio del @RequestParam al service y lo que retorna el service se guarda
        //en una variable de tipo BookDto

        if (books == null) {//Se hace validation de que la información que retorna del service y se ha guardado en la variable book no venga vaciá
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//En caso de que venga vaciá se enviá un error controlado al cliente
        }
        return new ResponseEntity<>(books, HttpStatus.OK);//Si todo esta bien se retorna el libro que fue encontrado por el titulo que se asigno en la url
    }

    @GetMapping(value = "/books/showBooks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> showBooks() {

        try {
            List<BookDto> books = service.showBooks();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Total de libros", String.valueOf(books.size()));
            return new ResponseEntity<>(books, headers, HttpStatus.OK);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la base de datos");
        }

    }

    @GetMapping(value = "/books/showAuthors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> showAuthors() {
        try {
            List<AuthorDto> authors = service.showAuthors();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Total de Autores registrados", String.valueOf(authors.size()));
            return new ResponseEntity(authors, headers, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la base de datos");
        }

    }

    @GetMapping(value = "/books/author", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuthorDto>> showAuthorsByYear(@RequestParam("year") String year) {
        if (year == null || !year.matches("^\\d{1,4}$")) {
            throw new IllegalArgumentException("El parámetro 'year' es inválido. Debe contener solo dígitos.");
        }
        List<AuthorDto> nameAuthors = service.showAuthorsByYear(year);
        if (nameAuthors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<>(nameAuthors, HttpStatus.OK);
    }

    @GetMapping(value = "/books/by-language", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <List<BookDto>> showBooksByLanguage (@RequestParam ("language") String languages){

        if (languages == null || languages.isBlank() || !languages.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("El String no es valido, ingresar un lenguaje valido");
        }
        List<BookDto> bookLanguages = service.showBooksByLanguages(languages);
        if (bookLanguages.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookLanguages, HttpStatus.OK);
    }
}