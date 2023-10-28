package com.example.library_management_platform.convertors;

import com.example.library_management_platform.models.api.request.AddBookGenre;
import com.example.library_management_platform.models.entities.BookGenre;
import com.example.library_management_platform.utils.StringUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class AddBookGenreRequestToBookGenreModelConvertor implements Converter<AddBookGenre, BookGenre> {
    @Override
    public BookGenre convert(AddBookGenre source) {
        BookGenre bookGenre =  new BookGenre();
        bookGenre.setName(source.getGenre().toLowerCase());
        bookGenre.setSlug(StringUtil.convertToSlug(source.getGenre()));
        return bookGenre;
    }
}
