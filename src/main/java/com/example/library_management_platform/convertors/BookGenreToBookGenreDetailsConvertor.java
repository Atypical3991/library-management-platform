package com.example.library_management_platform.convertors;

import org.springframework.core.convert.converter.Converter;
import com.example.library_management_platform.models.api.response.GetBookGenreById;
import com.example.library_management_platform.models.entities.BookGenre;
import org.springframework.stereotype.Component;


@Component
public class BookGenreToBookGenreDetailsConvertor implements Converter<BookGenre, GetBookGenreById.GenreDetails> {


    @Override
    public GetBookGenreById.GenreDetails convert(BookGenre bookGenre) {
        return new GetBookGenreById.GenreDetails(bookGenre.getId(), bookGenre.getName());
    }
}
