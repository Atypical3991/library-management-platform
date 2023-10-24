package com.example.library_management_platform.models.entities;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {
    private long id;
    private String username;
    private String contactEmail;
    private String contactNumber;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private Date dob;
    private List<String> activeIssuedBookIds;
    private List<String> pastIssuedBookIds;
    private String role;
    private Date createdAt;
    private Date updatedAt;
    private String status;
    private String address;
}
