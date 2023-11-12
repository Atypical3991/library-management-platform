package com.example.library_management_platform.models.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetBorrowerDetailsResponseModel extends BaseResponseModel {

    @JsonProperty("data")
    private BorrowerDetails data;

    public GetBorrowerDetailsResponseModel(Boolean success, String error, String message, BorrowerDetails data) {
        super(success, error, message);
        this.data = data;
    }

    public static class BorrowerDetails {
        @JsonProperty("email")
        private final String email;

        @JsonProperty("number")
        private final String number;

        @JsonProperty("username")
        private final String username;

        @JsonProperty("firstName")
        private final String firstName;

        @JsonProperty("lastName")
        private final String lastName;


        public BorrowerDetails(String email, String number, String username, String firstName, String lastName) {
            this.email = email;
            this.number = number;
            this.username = username;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }


}
