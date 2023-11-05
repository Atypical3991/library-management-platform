package com.example.library_management_platform.models.api.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class AddBookRequestModel {

    @NotBlank(message = "name of a Book can't be blank")
    @Pattern(regexp = "[a-zA-Z ]{3,50}", message = "Book name should contains only alphabets and spaces.")
    @JsonProperty("name")
    private String name;

    @NotEmpty(message = "genreIds shouldn't be empty list.")
    @JsonProperty("genreIds")
    private Set<Long> genreIds;

    @NotBlank(message = "Author of a Book shouldn't be empty.")
    @Pattern(regexp = "[a-zA-Z ]{3,50}", message = "Author name should contains only alphabets and spaces.")
    @JsonProperty("author")
    private String author;

    @NotBlank(message = "Publisher of a Book shouldn't be empty.")
    @Pattern(regexp = "[a-zA-Z ]{3,50}", message = "Publisher name should contains only alphabets and spaces.")
    @JsonProperty("publisher")
    private String publisher;

}
