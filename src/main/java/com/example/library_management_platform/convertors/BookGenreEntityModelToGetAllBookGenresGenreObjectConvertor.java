package com.example.library_management_platform.convertors;

import com.example.library_management_platform.models.api.response.GetAllBookGenresResponse;
import com.example.library_management_platform.models.entities.BookGenre;
import org.springframework.core.convert.converter.Converter;

public class BookGenreEntityModelToGetAllBookGenresGenreObjectConvertor implements Converter<BookGenre, GetAllBookGenresResponse.GenreObj> {
    @Override
    public GetAllBookGenresResponse.GenreObj convert(BookGenre source) {
        return new GetAllBookGenresResponse.GenreObj(source.getId(),source.getName());
    }
}
