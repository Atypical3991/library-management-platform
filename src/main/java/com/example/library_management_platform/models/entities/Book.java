package com.example.library_management_platform.models.entities;

import lombok.Data;
import lombok.Generated;

import java.util.Date;
import java.util.List;

@Data
public class Book {
    private long id;
    private String name;
    private String slugName;
    private List<Long> authorIds;
    private List<Long> genreIds;
    private Date addedAt;
    private Date updatedAt;
    private String status;
    private Long publisherId;
    private String cover;
}
