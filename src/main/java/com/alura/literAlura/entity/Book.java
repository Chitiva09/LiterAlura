package com.alura.literAlura.model;

import lombok.Getter;
import lombok.Setter;


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
@Getter
@Setter
private String title;
private String author;
private String year;
private String category;
private String editorial;
private String language;
private String image;
private String description;

}