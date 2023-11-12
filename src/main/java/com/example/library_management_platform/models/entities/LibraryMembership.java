package com.example.library_management_platform.models.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "library_membership")
public class LibraryMembership extends BaseEntity {

    @OneToOne(mappedBy = "libraryMembership", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Borrower borrower;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column(name = "booksCount")
    @Positive(message = "Books count must be positive")
    private Integer booksCount = 0;


    public enum StatusEnum {
        ACTIVE,
        IN_ACTIVE
    }

}
