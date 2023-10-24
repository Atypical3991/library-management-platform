package com.example.library_management_platform.models.entities;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Author {
    private long id;
    private String name;
    private String slugName;
    private List<String> bookIds;
    private List<String> genreIds;
    private Date createdAt;
    private Date updatedAt;
}
