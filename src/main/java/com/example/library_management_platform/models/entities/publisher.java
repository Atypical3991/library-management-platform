package com.example.library_management_platform.models.entities;


import lombok.Data;

import java.util.List;

@Data
public class publisher {
    private long id;
    private String name;
    private List<Long> bookIds;
    private List<Long> authorIds;
    private List<Long> genreIds;
}
