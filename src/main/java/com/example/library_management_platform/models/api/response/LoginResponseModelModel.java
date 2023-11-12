package com.example.library_management_platform.models.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginResponseModelModel extends BaseResponseModel {

    @JsonProperty("data")
    private LoginResponseDetailsData data;

    public LoginResponseModelModel(Boolean success, String error, String message, LoginResponseDetailsData data) {
        super(success, error, message);
        this.data = data;
    }

    public static class LoginResponseDetailsData {
        @JsonProperty("token")
        private final String token;

        public LoginResponseDetailsData(String token) {
            this.token = token;
        }
    }
}
