package com.example.library_management_platform.models.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestModel {

    @NotBlank
    @JsonProperty("username")
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$", message = "Username should contains alphabets and number with spaces and underscores.\n Username length should be between 3 to 20.")
    private String username;

    @NotBlank
    @JsonProperty("password")
    @Size(min = 5, max = 50)
    private String password;
}
