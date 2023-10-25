package com.example.library_management_platform.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="book")
public class Book  extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "author_ids")
    private List<Long> authorIds;

    @Column(name="genre_ids")
    private List<Long> genre_ids;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private StatusEnum status;

    @JoinColumn(name = "publisher_id")
    private Long publisher_id;

    @Enumerated(EnumType.STRING)
    @Column(name="cover")
    private CoverEnum cover;


    public enum StatusEnum{
        ACTIVE,
        IN_ACTIVE
    }

    public enum CoverEnum{
        PAPERBACK,
        HARDCOVER
    }
}
