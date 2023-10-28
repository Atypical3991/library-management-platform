package com.example.library_management_platform.models.api.request;

import lombok.Data;

import java.util.Date;

@Data
public class AddUserRequest {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String contactEmail;
    private String gender;
    private Date dob;
    private String address;
    private String role;
}
