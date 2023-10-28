package com.example.library_management_platform.repositories;

import com.example.library_management_platform.models.entities.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookGenreRepository extends JpaRepository<BookGenre, Long> {
}
