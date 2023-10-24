package com.example.library_management_platform.models.api.response;


import lombok.Data;

@Data
public class BaseResponse {
    private Boolean success;
    private String error;
    private String message;
}
