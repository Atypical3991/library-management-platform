package com.example.library_management_platform.models.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateBookGenreRequestModel {


    @JsonProperty("name")
    private String name;
}
