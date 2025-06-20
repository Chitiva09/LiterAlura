package com.alura.literAlura.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


import com.alura.literAlura.dto.BookDto;
import com.alura.literAlura.dto.ResponseGutendexClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Client {

    String url = "https://gutendex.com/books?";

    public List<BookDto> requestBook() throws IOException, JsonProcessingException, InterruptedException{

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        ObjectMapper mapper = new ObjectMapper();
        
        ResponseGutendexClient responseGutendexClient = mapper.readValue(json, ResponseGutendexClient.class);

        return responseGutendexClient != null ? responseGutendexClient : new ResponseGutendexClient();
    }
}