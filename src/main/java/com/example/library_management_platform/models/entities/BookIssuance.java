package com.example.library_management_platform.models.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "book_issuance")
public class BookIssuance extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private Borrower borrower;

    @Column(name = "issued_at")
    private Date issuedAt;

    @Column(name = "expired_at")
    private Date expiredAt;

    @Column(name = "book_id")
    private Long bookId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + this.getId() +
                ", bookId='" + this.getBookId() + '\'' +
                ", borrower=" + this.getBorrower() +
                '}';
    }

    public enum StatusEnum {
        REQUESTED,
        APPROVED,
        REJECTED,
        DELIVERED,
        RETURNED
    }
}
