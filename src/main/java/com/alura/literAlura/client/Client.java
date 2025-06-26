package com.alura.literAlura.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


import com.alura.literAlura.dto.BookDto;
import com.alura.literAlura.dto.GutendexResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Component;

@Component
public class Client {


    public List<BookDto> requestBook(String title) throws JsonProcessingException {

        String url = "https://gutendex.com/books/?search=" + title;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        String json = response.body();//Guardo la respuesta en un String

        ObjectMapper mapper = new ObjectMapper();
        GutendexResponse gutendexResponse = mapper.readValue(json, GutendexResponse.class);// Envia la información del Json a la clase GutendexResponse

        return gutendexResponse.getResults(); //Obtiene la información de la clase GutendexResponse la cual retorna un List<BookDto>
    }
}