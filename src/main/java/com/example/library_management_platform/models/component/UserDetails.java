package com.example.library_management_platform.models.component;


import lombok.Data;

@Data
public class UserDetails {
    private String username;
    private Long userId;
    private String role;

    public UserDetails(String username, Long userId, String role) {
        this.username = username;
        this.userId = userId;
        this.role = role;
    }
}
