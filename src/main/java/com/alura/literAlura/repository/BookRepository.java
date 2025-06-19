package com.alura.literAlura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.literAlura.entity.Author;
import com.alura.literAlura.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);
    List<Book> showBooks();
    List<Author> showAuthors();
    List<Author> showAuthorsByYear(String year);
    List<Book> showBooksByLanguage(String language);

}