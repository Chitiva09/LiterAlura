package com.alura.literAlura.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

@Table(name = "books")
public class Book implements Serializable {

    /*
     * titulo
     * autor
     * año
     * categoria
     * editorial
     * idioma
     * imagen
     * descripcion
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String title;
    @ManyToMany
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))// Define la tabla intermedia book_author con las claves foráneas a books y authors
    @JsonManagedReference
    private List<Author> authors;
    @Lob
    private String category;
    @Column(length = 500)
    private String languages;
    @Lob
    private String image;
    @Lob
    private String description;

}