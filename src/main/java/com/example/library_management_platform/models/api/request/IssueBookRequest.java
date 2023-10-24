package com.example.library_management_platform.models.api.request;

import lombok.Data;

import java.util.Date;

@Data
public class IssueBookRequest {
    private Long userId;
    private Long bookId;
    private Date issueAt;
    private Date issueExpireAt;
}
