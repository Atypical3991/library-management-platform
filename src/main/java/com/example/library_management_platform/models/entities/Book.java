package com.example.library_management_platform.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book", uniqueConstraints = {
        @UniqueConstraint(columnNames = "slug")
})
public class Book extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "author")
    private String author;

    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    private Set<BookGenre> bookGenres = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;

    @Column(name = "publisher")
    private String publisher;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ",  genres=" + this.getBookGenres().stream().map(BookGenre::getName).toList() +
                '}';
    }

    public enum StatusEnum {
        ISSUANCE_REQUESTED,
        ACTIVE,
        IN_ACTIVE
    }

}
