package com.example.library_management_platform.models.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GetAllBooksResponse extends BaseResponse {


    private GetAllBooksData data;

    public GetAllBooksResponse(Boolean success, String error, String message, GetAllBooksData data) {
        super(success, error, message);
        this.data = data;
    }

    public static class GetAllBooksData{

        @JsonProperty("count")
        private Integer count;

        @JsonProperty("books")
        private List<BookDetails> books;

        public GetAllBooksData(Integer count, List<BookDetails> books) {
            this.count = count;
            this.books = books;
        }

        @Data
        public static class BookDetails{
            @JsonProperty("id")
            private long id;
            @JsonProperty("name")
            private String name;
            @JsonProperty("author")
            private String author;
            @JsonProperty("genres")
            private List<String> genres;
            @JsonProperty("publisher")
            private String publisher;

            public BookDetails(long id, String name, String author, List<String> genres, String publisher) {
                this.id = id;
                this.name = name;
                this.author = author;
                this.genres = genres;
                this.publisher = publisher;
            }
        }

    }

}
