package com.example.library_management_platform.models.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="author")
public class Author extends BaseEntity{

    @Column(name="name")
    private String name;

    @Column(name="book_ids")
    private List<Long> bookIds;

    @Column(name="genre_ids")
    private List<Long> genreIds;


}
