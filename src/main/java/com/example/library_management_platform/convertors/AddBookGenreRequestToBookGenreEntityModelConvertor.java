package com.example.library_management_platform.convertors;

import com.example.library_management_platform.models.api.request.AddBookGenreRequestModel;
import com.example.library_management_platform.models.entities.BookGenre;
import com.example.library_management_platform.utils.StringUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class AddBookGenreRequestToBookGenreEntityModelConvertor implements Converter<AddBookGenreRequestModel, BookGenre> {
    @Override
    public BookGenre convert(AddBookGenreRequestModel source) {
        BookGenre bookGenre = new BookGenre();
        bookGenre.setName(source.getGenre().toLowerCase());
        bookGenre.setSlug(StringUtil.convertToSlug(source.getGenre()));
        return bookGenre;
    }
}
