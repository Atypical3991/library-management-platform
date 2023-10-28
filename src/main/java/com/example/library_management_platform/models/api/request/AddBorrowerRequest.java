package com.example.library_management_platform.models.api.request;

import com.example.library_management_platform.models.entities.Borrower;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddBorrowerRequest {

    @Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$", message = "Username should contains alphabets and number with spaces and underscores.\n Username length should be between 3 to 20.")
    @JsonProperty("username")
    private String userName;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "Password should be: \n" +
            "At least one alphabet character (uppercase or lowercase).\n" +
            "At least one digit.\n" +
            "At least one special character from the set [@$!%*?&].\n" +
            "Minimum length of 8 characters.")
    @JsonProperty("password")
    private String password;

    @Pattern(regexp = "^[a-zA-Z ]{3,20}$", message = "First name should contains alphabets and spaces with min length 3 and max length 20.")
    @JsonProperty("firstName")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z ]{3,20}$", message = "Last name should contains alphabets and spaces with min length 3 and max length 20.")
    @JsonProperty("lastName")
    private String lastName;

    @Pattern(regexp = "^\\d{10}$\n", message = "Contact number must be 10 digit long.")
    @JsonProperty("contactNumber")
    private String contactNumber;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Please pass a valid email id")
    @JsonProperty("contactEmail")
    private String contactEmail;


    @JsonProperty("gender")
    private Borrower.GenderEnum gender;

    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$",message = "Please DOB in DD/MM/YYYY format")
    @JsonProperty("dob")
    private String dob;

    @Size(min = 15, max = 100, message = "Address length should be min 15  and max 100.")
    @JsonProperty("address")
    private String address;
}
