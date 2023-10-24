package com.example.library_management_platform.models.api.response;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GetBookDetailsResponse {

    private GetBookDetailsData data;
    @Data
    public class GetBookDetailsData{
        private Long Id;
        private List<String> authors;
        private List<String> genres;
        private String publisher;
        private String cover;
        private Date dateAdded;
        private Integer issuedByCount;
    }

}
