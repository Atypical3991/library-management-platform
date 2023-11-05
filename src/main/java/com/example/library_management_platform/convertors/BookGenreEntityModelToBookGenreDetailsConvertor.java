package com.example.library_management_platform.convertors;

import org.springframework.core.convert.converter.Converter;
import com.example.library_management_platform.models.api.response.GetBookGenreByIdResponseModel;
import com.example.library_management_platform.models.entities.BookGenre;
import org.springframework.stereotype.Component;


@Component
public class BookGenreEntityModelToBookGenreDetailsConvertor implements Converter<BookGenre, GetBookGenreByIdResponseModel.GenreDetails> {


    @Override
    public GetBookGenreByIdResponseModel.GenreDetails convert(BookGenre bookGenre) {
        return new GetBookGenreByIdResponseModel.GenreDetails(bookGenre.getId(), bookGenre.getName());
    }
}
