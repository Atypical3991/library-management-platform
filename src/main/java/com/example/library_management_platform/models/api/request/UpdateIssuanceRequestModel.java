package com.example.library_management_platform.models.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateIssuanceRequestModel {

    @JsonProperty("issuanceId")
    private long issuanceId;

    @JsonProperty("status")
    @Schema( allowableValues = "rejected, returned")
    private BookIssuanceStatusEnum status;


    public enum BookIssuanceStatusEnum {
        rejected,
        returned
    }

}
