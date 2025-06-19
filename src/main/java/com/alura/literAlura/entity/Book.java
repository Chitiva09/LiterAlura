package com.alura.literAlura.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

@Table(name = "books")
public class Book {

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
@ManyToOne
@JoinColumn(name = "author_id")//Establece la clave foranea para la conexion con la tabla authors
private Author author;
private String year;
private String category;
private String editorial;
private String language;
private String image;
private String description;

}