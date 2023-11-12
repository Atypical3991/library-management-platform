package com.example.library_management_platform.convertors;

import com.example.library_management_platform.models.api.response.GetBookGenreByIdResponseModel;
import com.example.library_management_platform.models.entities.BookGenre;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class BookGenreEntityModelToBookGenreDetailsConvertor implements Converter<BookGenre, GetBookGenreByIdResponseModel.BookGenreByIdDetails> {


    @Override
    public GetBookGenreByIdResponseModel.BookGenreByIdDetails convert(BookGenre bookGenre) {
        return new GetBookGenreByIdResponseModel.BookGenreByIdDetails(bookGenre.getId(), bookGenre.getName());
    }
}
