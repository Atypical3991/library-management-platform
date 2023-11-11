package com.example.library_management_platform.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sessions")
public class Sessions extends BaseEntity {
    @Column(name = "token")
    private String token;
    @Column(name = "user_id")
    private long userId;
}
