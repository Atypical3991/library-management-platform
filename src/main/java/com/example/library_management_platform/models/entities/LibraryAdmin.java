package com.example.library_management_platform.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "admin")
public class LibraryAdmin extends BaseEntity {
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}
