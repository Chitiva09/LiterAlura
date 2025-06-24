package com.alura.literAlura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexResponse {//esta clase hace que solo obtenga la información que viene dentro del result del json
    private List<BookDto> results;

}
