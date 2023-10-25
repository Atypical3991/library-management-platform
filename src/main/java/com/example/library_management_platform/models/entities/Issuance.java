package com.example.library_management_platform.models.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="issuance")
public class Issuance extends BaseEntity {

    @Column(name="user_id")
    private Long userId;

    @Column(name="expire_at")
    private Date expiredAt;

    @Column(name = "book_id")
    private Long bookId;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private StatusEnum status;

    public enum StatusEnum{
        ACTIVE,
        IN_ACTIVE
    }
}
