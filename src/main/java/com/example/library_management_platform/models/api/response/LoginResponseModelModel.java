package com.example.library_management_platform.models.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginResponseModelModel extends BaseResponseModel {

    @JsonProperty("data")
    private DataObj data;

    public LoginResponseModelModel(Boolean success, String error, String message, DataObj data) {
        super(success, error, message);
        this.data = data;
    }

    public static class DataObj{
        @JsonProperty("token")
        private String token;

        public DataObj(String token) {
            this.token = token;
        }
    }
}
