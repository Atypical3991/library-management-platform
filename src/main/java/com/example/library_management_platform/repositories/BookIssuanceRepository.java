package com.example.library_management_platform.repositories;

import com.example.library_management_platform.models.entities.BookIssuance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookIssuanceRepository extends JpaRepository<BookIssuance,Long> {
    List<BookIssuance> findAllByStatus(BookIssuance.StatusEnum status);
}
