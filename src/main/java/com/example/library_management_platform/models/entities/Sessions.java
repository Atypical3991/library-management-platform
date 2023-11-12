package com.example.library_management_platform.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sessions", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "role"})

}, indexes = {
        @Index(name = "token_index", columnList = "token")
})
public class Sessions extends BaseEntity {

    @Column(name = "token")
    private String token;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private User.RoleEnum role;
}
