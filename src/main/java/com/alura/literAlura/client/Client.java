package com.alura.literAlura.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.alura.literAlura.model.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Client {

    String url = "https://gutendex.com/books?";

    public Book requestBook() throws IOException, JsonProcessingException, InterruptedException{

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        ObjectMapper mapper = new ObjectMapper();
        
        Book book = mapper.readValue(json, Book.class);

        return book;
    }
}