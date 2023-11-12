package com.example.library_management_platform.models.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateBookIssuanceRequestModel {

    @JsonProperty("bookId")
    private long bookId;

    @JsonProperty("borrowerId")
    private long borrowerId;

    @NotBlank(message = "startDate can't be empty")
    @JsonProperty("startDate")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$", message = "Please pass startDate in DD/MM/YYYY format")
    private String startDate;

    @NotBlank(message = "endDate can't be empty")
    @JsonProperty("endDate")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$", message = "Please pass endDate in DD/MM/YYYY format")
    private String endDate;
}
