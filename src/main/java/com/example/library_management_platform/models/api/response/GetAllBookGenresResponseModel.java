package com.example.library_management_platform.models.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetAllBookGenresResponseModel extends BaseResponseModel {

    @JsonProperty("data")
    private DataObj data;

    public GetAllBookGenresResponseModel(Boolean success, String error, String message, DataObj data) {
        super(success, error, message);
        this.data = data;
    }

    @Data
    public static class DataObj{

        @JsonProperty("genres")
        private List<GenreObj> genres;

        public DataObj(List<GenreObj> genres) {
            this.genres = genres;
        }
    }

    @Data
    public static class GenreObj  {

        private Long id;
        private String name;

        public GenreObj(Long id, String name) {
            this.setId(id);
            this.setName(name);
        }
    }
}
