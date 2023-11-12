package com.example.library_management_platform.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_genre", uniqueConstraints = {
        @UniqueConstraint(columnNames = "slug")
})
public class BookGenre extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "books")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "genre_book",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;

    @Override
    public String toString() {
        return "BookGenre{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ",  genres=" + this.getBooks().stream().map(Book::getName).toList() +
                '}';
    }
}
