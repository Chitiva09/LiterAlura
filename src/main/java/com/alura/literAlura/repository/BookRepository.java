package com.alura.literAlura.repository;

import java.util.List;

import com.alura.literAlura.dto.BookDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.literAlura.entity.Author;
import com.alura.literAlura.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitle(String title);
    List<Book> findAll ();
    List<Book> findByLanguagesContaining (String languages);

}