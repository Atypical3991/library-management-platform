package com.example.library_management_platform.repositories;

import com.example.library_management_platform.models.entities.BookIssuance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookIssuanceRepository extends JpaRepository<BookIssuance,Long> {
}
