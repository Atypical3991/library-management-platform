package com.example.library_management_platform.models.entities;


import lombok.Data;

import java.util.Date;

@Data
public class Issuance {
    private long id;
    private long userId;
    private Date createdAt;
    private Date expiredAt;
    private Date updatedAt;
    private long bookId;
    private String status;
}
