package com.example.library_management_platform.models.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetAllBookGenresResponseModel extends BaseResponseModel {

    @JsonProperty("data")
    private AllGenreDetailsData data;

    public GetAllBookGenresResponseModel(Boolean success, String error, String message, AllGenreDetailsData data) {
        super(success, error, message);
        this.data = data;
    }

    @Data
    public static class AllGenreDetailsData {

        @JsonProperty("genres")
        private List<AllGenreObj> genres;

        public AllGenreDetailsData(List<AllGenreObj> genres) {
            this.genres = genres;
        }
    }

    @Data
    public static class AllGenreObj {

        private Long id;
        private String name;

        public AllGenreObj(Long id, String name) {
            this.setId(id);
            this.setName(name);
        }
    }
}
