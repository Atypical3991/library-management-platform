package com.example.library_management_platform.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "borrower", uniqueConstraints = {
        @UniqueConstraint(columnNames = "contact_email"),
        @UniqueConstraint(columnNames = "contact_number"),
        @UniqueConstraint(columnNames = "username")
})
public class Borrower extends User {

    @Column(name = "username")
    private String username;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderEnum gender;

    @Column(name = "dob")
    private String dob;

    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "book_issuance_list")
    private List<BookIssuance> bookIssuanceList;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;

    @Column(name = "address")
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_membership_id")
    private LibraryMembership libraryMembership;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + this.getId() +
                ", contactEmail='" + this.getContactEmail() + '\'' +
                ", username=" + this.getUsername() +
                '}';
    }

    public enum StatusEnum {
        ACTIVE,
        IN_ACTIVE
    }

    public enum GenderEnum {
        MALE,
        FEMALE
    }
}
