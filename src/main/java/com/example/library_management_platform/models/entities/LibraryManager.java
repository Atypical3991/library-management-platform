package com.example.library_management_platform.models.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "library_manager", uniqueConstraints = {
        @UniqueConstraint(columnNames = "contact_email"),
        @UniqueConstraint(columnNames = "contact_number"),
        @UniqueConstraint(columnNames = "username")
})
public class LibraryManager extends User {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_number")
    private String contactNumber;
}
