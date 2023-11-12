package com.example.library_management_platform.repositories;

import com.example.library_management_platform.models.entities.Sessions;
import com.example.library_management_platform.models.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionsRepository extends JpaRepository<Sessions, Long> {


    @Transactional
    int deleteSessionsByToken(String token);

    void deleteAllByUserIdAndRole(Long userId, User.RoleEnum role);


    Sessions findTopByToken(String token);
}
