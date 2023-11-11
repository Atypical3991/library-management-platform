package com.example.library_management_platform.convertors;

import com.example.library_management_platform.models.api.response.GetAllBookGenresResponseModel;
import com.example.library_management_platform.models.entities.BookGenre;
import org.springframework.core.convert.converter.Converter;

public class BookGenreEntityModelToGetAllBookGenresGenreObjConvertor implements Converter<BookGenre, GetAllBookGenresResponseModel.AllGenreObj> {
    @Override
    public GetAllBookGenresResponseModel.AllGenreObj convert(BookGenre source) {
        return new GetAllBookGenresResponseModel.AllGenreObj(source.getId(),source.getName());
    }
}
