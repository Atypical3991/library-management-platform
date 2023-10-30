package com.example.library_management_platform.models.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;

@Data
public class GetAllIssuanceResponse extends BaseResponse {

    @JsonProperty("data")
    private DataObj data;

    public GetAllIssuanceResponse(Boolean success, String error, String message, DataObj data) {
        super(success, error, message);
        this.data = data;
    }

    @Data
    public static class DataObj {
        private List<IssuanceObj> issuanceObjList;

        public DataObj(List<IssuanceObj> issuanceObjList) {
            this.issuanceObjList = issuanceObjList;
        }
    }

    @Data
    public static class IssuanceObj{
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


        public IssuanceObj(String bookName, String username, String startDate, String endDate, String status) {
            this.bookName = bookName;
            this.username = username;
            this.startDate = startDate;
            this.endDate = endDate;
            this.status = status;
        }
    }
}
