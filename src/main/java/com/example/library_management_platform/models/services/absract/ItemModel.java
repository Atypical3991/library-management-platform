package com.example.library_management_platform.models.services.absract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Date;


@Data
public class ItemModel {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;
}
