package com.alura.literAlura.entity;

import java.io.Serializable;
import java.util.List;

import aj.org.objectweb.asm.commons.Remapper;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "authors")
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(mappedBy = "authors")
    @JsonBackReference
    private List<Book> books;

    @Column(unique = true)
    private String name;
    private int birthYear;
    private int deathYear;

}
