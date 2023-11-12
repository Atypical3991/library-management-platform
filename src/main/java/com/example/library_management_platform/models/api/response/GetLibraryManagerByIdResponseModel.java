package com.example.library_management_platform.models.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetLibraryManagerByIdResponseModel extends BaseResponseModel {

    @JsonProperty("data")
    private LibraryManagerByIdDetailsData data;

    public GetLibraryManagerByIdResponseModel(Boolean success, String error, String message, LibraryManagerByIdDetailsData data) {
        super(success, error, message);
        this.data = data;
    }

    @Data
    public static class LibraryManagerByIdDetailsData {

        @JsonProperty("username")
        private String username;

        @JsonProperty("first_name")
        private String firstName;

        @JsonProperty("last_name")
        private String lastName;

        @JsonProperty("contact_email")
        private String contactEmail;

        @JsonProperty("contact_number")
        private String contactNumber;

        public LibraryManagerByIdDetailsData(String username, String firstName, String lastName, String contactEmail, String contactNumber) {
            this.username = username;
            this.firstName = firstName;
            this.lastName = lastName;
            this.contactEmail = contactEmail;
            this.contactNumber = contactNumber;
        }
    }
}
