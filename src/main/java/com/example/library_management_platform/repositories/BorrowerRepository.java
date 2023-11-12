package com.example.library_management_platform.repositories;

import com.example.library_management_platform.models.entities.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
    Borrower findTopByUsernameAndPassword(String username, String password);
}
