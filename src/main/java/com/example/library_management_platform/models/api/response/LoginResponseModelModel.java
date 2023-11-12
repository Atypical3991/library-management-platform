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

    @Data
    public static class LoginResponseDetailsData {
        @JsonProperty("token")
        private String token;

        public LoginResponseDetailsData(String token) {
            this.token = token;
        }
    }
}
