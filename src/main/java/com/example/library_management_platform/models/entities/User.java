package com.example.library_management_platform.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public class User extends BaseEntity {

    @Column(name= "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public enum RoleEnum{
        borrower, library_manager
    }

}
