package com.example.library_management_platform.repositories;

import com.example.library_management_platform.models.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
}
