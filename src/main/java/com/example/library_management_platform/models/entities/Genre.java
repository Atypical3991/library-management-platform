package com.example.library_management_platform.models.entities;

import lombok.Data;

import java.util.List;

@Data
public class Genre {
    private long id;
    private String name;
    private String slugName;
    private List<String> bookIds;
    private List<String> authorIds;
}
