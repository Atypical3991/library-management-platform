package com.example.library_management_platform.repositories;

import com.example.library_management_platform.models.entities.LibraryAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryAdminRepository extends JpaRepository<LibraryAdmin, Long> {

    LibraryAdmin findTopByUsernameAndPassword(String username, String password);
}
