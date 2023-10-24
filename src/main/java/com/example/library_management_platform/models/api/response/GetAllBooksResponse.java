package com.example.library_management_platform.models.api.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GetAllBooksResponse extends BaseResponse {


    private GetAllBooksData data;

    public class GetAllBooksData{
        private Integer count;
        private List<BookDetails> books;
        @Data
        public class BookDetails{
            private long id;
            private String name;
            private List<String> authors;
            private List<String> genres;
            private Long publisherId;
            private Date dateAdded;
            private String cover;
        }

    }




}
