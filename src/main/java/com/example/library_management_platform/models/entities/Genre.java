package com.example.library_management_platform.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="genre")
public class Genre extends BaseEntity {

    @Column(name="name")
    private String name;

    @Column(name="book_ids")
    private List<Long> book_ids;

    @Column(name="author_ids")
    private List<Long> author_ids;
}
