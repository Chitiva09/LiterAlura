package com.alura.literAlura.repository;

import com.alura.literAlura.entity.Author;
import com.alura.literAlura.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

        Optional<Author> findByName(String name);

        Author save(Author author);
    }


