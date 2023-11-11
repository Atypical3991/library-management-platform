package com.example.library_management_platform.models.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class RenewMembershipRequestModel {

    @JsonProperty("issuedAt")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$",message = "Please pass startDate in DD/MM/YYYY format")
    private String issuedAt;

    @JsonProperty("expiredAt")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$",message = "Please pass startDate in DD/MM/YYYY format")
    private String expiredAt;

    @JsonProperty("borrowerId")
    private long membershipId;
}
