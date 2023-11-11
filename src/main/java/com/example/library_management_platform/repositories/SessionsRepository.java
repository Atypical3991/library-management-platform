package com.example.library_management_platform.repositories;

import com.example.library_management_platform.models.entities.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionsRepository extends JpaRepository<Sessions, Long> {

    void deleteByToken(String token);
}
