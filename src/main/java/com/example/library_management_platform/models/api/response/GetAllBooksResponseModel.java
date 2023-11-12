package com.example.library_management_platform.models.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetAllBooksResponseModel extends BaseResponseModel {


    @JsonProperty("data")
    private AllBookDetailsData data;

    public GetAllBooksResponseModel(Boolean success, String error, String message, AllBookDetailsData data) {
        super(success, error, message);
        this.data = data;
    }

    public static class AllBookDetailsData {

        @JsonProperty("count")
        private final Integer count;

        @JsonProperty("books")
        private final List<AllBookDetails> books;

        public AllBookDetailsData(Integer count, List<AllBookDetails> books) {
            this.count = count;
            this.books = books;
        }

        @Data
        public static class AllBookDetails {
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

            public AllBookDetails(long id, String name, String author, List<String> genres, String publisher) {
                this.id = id;
                this.name = name;
                this.author = author;
                this.genres = genres;
                this.publisher = publisher;
            }
        }

    }

}
