package com.example.library_management_platform.models.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public class GetBookGenreById extends BaseResponse{
    @JsonProperty("data")
    private DataObj data;

    public GetBookGenreById(Boolean success, String error, String message, DataObj data) {
        super(success, error, message);
        this.data = data;
    }

    @Data
    public static class DataObj{

        @JsonProperty("genre")
        private GenreDetails genre;

        public DataObj(GenreDetails genre) {
            this.genre = genre;
        }
    }

    @Data
    public static class GenreDetails{
        @JsonProperty("id")
        private Long id;
        @JsonProperty("name")
        private String name;

        public GenreDetails(Long id, String name) {
            this.id = id;
            this.name = name;
        }
//        private List<Book> books;
//        private List<Author> authors;
    }




}
