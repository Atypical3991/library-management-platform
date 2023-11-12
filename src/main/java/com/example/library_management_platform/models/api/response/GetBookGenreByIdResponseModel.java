package com.example.library_management_platform.models.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public class GetBookGenreByIdResponseModel extends BaseResponseModel {
    @JsonProperty("data")
    private final BookGenreByIdDetailsData data;

    public GetBookGenreByIdResponseModel(Boolean success, String error, String message, BookGenreByIdDetailsData data) {
        super(success, error, message);
        this.data = data;
    }

    @Data
    public static class BookGenreByIdDetailsData {

        @JsonProperty("genre")
        private BookGenreByIdDetails genre;

        public BookGenreByIdDetailsData(BookGenreByIdDetails genre) {
            this.genre = genre;
        }
    }

    @Data
    public static class BookGenreByIdDetails {
        @JsonProperty("id")
        private Long id;
        @JsonProperty("name")
        private String name;

        public BookGenreByIdDetails(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }


}
