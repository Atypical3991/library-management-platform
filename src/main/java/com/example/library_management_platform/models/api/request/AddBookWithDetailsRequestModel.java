package com.example.library_management_platform.models.api.request;

import lombok.Data;

import java.util.List;

@Data
public class AddBookWithDetailsRequestModel {
    private String name;
    private List<Long> genreIds;
    private List<Long> authorIds;
    private Long publisherId;
    private String cover;
}
