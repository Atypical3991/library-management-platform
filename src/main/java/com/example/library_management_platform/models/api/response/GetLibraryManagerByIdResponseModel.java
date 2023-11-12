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

    public static class LibraryManagerByIdDetailsData {

        @JsonProperty("username")
        private final String username;

        @JsonProperty("first_name")
        private final String firstName;

        @JsonProperty("last_name")
        private final String lastName;

        @JsonProperty("contact_email")
        private final String contactEmail;

        @JsonProperty("contact_number")
        private final String contactNumber;

        public LibraryManagerByIdDetailsData(String username, String firstName, String lastName, String contactEmail, String contactNumber) {
            this.username = username;
            this.firstName = firstName;
            this.lastName = lastName;
            this.contactEmail = contactEmail;
            this.contactNumber = contactNumber;
        }
    }
}
