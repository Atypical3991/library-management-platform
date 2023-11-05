package com.example.library_management_platform.convertors;

import com.example.library_management_platform.models.api.response.GetAllBookGenresResponseModel;
import com.example.library_management_platform.models.entities.BookGenre;
import org.springframework.core.convert.converter.Converter;

public class BookGenreEntityModelToGetAllBookGenresGenreObjConvertor implements Converter<BookGenre, GetAllBookGenresResponseModel.GenreObj> {
    @Override
    public GetAllBookGenresResponseModel.GenreObj convert(BookGenre source) {
        return new GetAllBookGenresResponseModel.GenreObj(source.getId(),source.getName());
    }
}
