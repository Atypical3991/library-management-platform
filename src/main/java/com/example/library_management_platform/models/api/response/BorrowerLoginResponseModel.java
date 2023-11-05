package com.example.library_management_platform.models.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BorrowerLoginResponseModel extends BaseResponse{

    @JsonProperty("data")
    private DataObj data;

    public BorrowerLoginResponseModel(Boolean success, String error, String message, DataObj data) {
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
