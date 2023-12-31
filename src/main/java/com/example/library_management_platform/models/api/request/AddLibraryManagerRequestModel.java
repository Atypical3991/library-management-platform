package com.example.library_management_platform.models.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AddLibraryManagerRequestModel {

    @NotBlank(message = "username can't be empty")
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$", message = "Username should contains alphabets and number with spaces and underscores.\n Username length should be between 3 to 20.")
    @JsonProperty("username")
    private String userName;

    @NotBlank(message = "password can't be empty")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password should be: \n" +
            "At least one alphabet character (uppercase or lowercase).\n" +
            "At least one digit.\n" +
            "At least one special character from the set [@$!%*?&].\n" +
            "Minimum length of 8 characters.")
    @JsonProperty("password")
    private String password;

    @NotBlank(message = "first name can't be empty")
    @Pattern(regexp = "^[a-zA-Z ]{3,20}$", message = "First name should contains alphabets and spaces with min length 3 and max length 20.")
    @JsonProperty("firstName")
    private String firstName;

    @NotBlank(message = "last name can't be empty")
    @Pattern(regexp = "^[a-zA-Z ]{3,20}$", message = "Last name should contains alphabets and spaces with min length 3 and max length 20.")
    @JsonProperty("lastName")
    private String lastName;

    @NotBlank(message = "contact number can't be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Contact number must be 10 digit long.")
    @JsonProperty("contactNumber")
    private String contactNumber;

    @NotBlank(message = "email can't be empty")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Please pass a valid email id")
    @JsonProperty("contactEmail")
    private String contactEmail;
}
