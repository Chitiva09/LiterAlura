package com.alura.literAlura.dto;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto {

    private String title;
    private List<AuthorDto> authors;
    @JsonProperty("summaries")
    private List<String> description;

    @JsonProperty("subjects")
    private List<String> category;
    private List<String> languages;
    private Map<String, String> formats;
    private String image;

}
