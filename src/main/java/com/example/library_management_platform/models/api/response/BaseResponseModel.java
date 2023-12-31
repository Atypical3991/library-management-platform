package com.example.library_management_platform.models.api.response;


import lombok.Data;

@Data
public class BaseResponseModel {
    private Boolean success;
    private String error;
    private String message;

    public BaseResponseModel(Boolean success, String error, String message) {
        this.success = success;
        this.error = error;
        this.message = message;
    }

    public BaseResponseModel() {
    }
}
