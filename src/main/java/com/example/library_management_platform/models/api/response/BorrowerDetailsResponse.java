package com.example.library_management_platform.models.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BorrowerDetailsResponse  extends BaseResponse{

    @JsonProperty("data")
    private DataObj data;

    public BorrowerDetailsResponse(Boolean success, String error, String message, DataObj data) {
        super(success, error, message);
        this.data = data;
    }

    public static class DataObj{
        @JsonProperty("email")
        private String email;

        @JsonProperty("number")
        private String number;

        @JsonProperty("username")
        private String username;

        @JsonProperty("firstName")
        private String firstName;

        @JsonProperty("lastName")
        private String lastName;

        @JsonProperty("issuanceList")
        private List<Long> issuanceList;


        public DataObj(String email, String number, String username, String firstName, String lastName, List<Long> issuanceList) {
            this.email = email;
            this.number = number;
            this.username = username;
            this.firstName = firstName;
            this.lastName = lastName;
            this.issuanceList = issuanceList;
        }

    }


}
