package com.example.library_management_platform.models.api.request;


import com.example.library_management_platform.models.services.absract.CreateItemModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AddBookGenre {

    @NotBlank(message = "Genre shouldn't be empty")
    @Pattern(regexp = "[a-zA-Z ]{3,30}", message = "Genre should contains only alphabets and spaces.")
    @JsonProperty("genre")
    private String genre;


}
