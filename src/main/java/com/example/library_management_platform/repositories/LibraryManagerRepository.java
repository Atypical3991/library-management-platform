package com.example.library_management_platform.repositories;

import com.example.library_management_platform.models.entities.Borrower;
import com.example.library_management_platform.models.entities.LibraryManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryManagerRepository extends JpaRepository<LibraryManager,Long> {

    LibraryManager findTopByUsernameAndPassword(String username, String password);
}
