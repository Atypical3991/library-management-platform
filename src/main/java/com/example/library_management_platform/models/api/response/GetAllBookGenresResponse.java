package com.example.library_management_platform.models.api.response;

import com.example.library_management_platform.models.services.absract.ItemModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetAllBookGenresResponse extends BaseResponse{

    @JsonProperty("data")
    private DataObj data;

    public GetAllBookGenresResponse(Boolean success, String error, String message, DataObj data) {
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
    public static class GenreObj extends ItemModel {
        public GenreObj(Long id, String name) {
            this.setId(id);
            this.setName(name);
        }
    }
}
