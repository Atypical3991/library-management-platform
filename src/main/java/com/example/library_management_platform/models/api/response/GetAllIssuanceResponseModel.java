package com.example.library_management_platform.models.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetAllIssuanceResponseModel extends BaseResponseModel {

    @JsonProperty("data")
    private AllIssuanceDetailsData data;

    public GetAllIssuanceResponseModel(Boolean success, String error, String message, AllIssuanceDetailsData data) {
        super(success, error, message);
        this.data = data;
    }

    @Data
    public static class AllIssuanceDetailsData {
        private List<AllIssuanceObj> allIssuanceObjList;

        public AllIssuanceDetailsData(List<AllIssuanceObj> allIssuanceObjList) {
            this.allIssuanceObjList = allIssuanceObjList;
        }
    }

    @Data
    public static class AllIssuanceObj {
        @JsonProperty("bookName")
        private String bookName;

        @JsonProperty("username")
        private String username;

        @JsonProperty("startDate")
        private String startDate;

        @JsonProperty("endDate")
        private String endDate;

        @JsonProperty("status")
        private String status;


        public AllIssuanceObj(String bookName, String username, String startDate, String endDate, String status) {
            this.bookName = bookName;
            this.username = username;
            this.startDate = startDate;
            this.endDate = endDate;
            this.status = status;
        }
    }
}
