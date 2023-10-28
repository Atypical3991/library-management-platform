package com.example.library_management_platform.convertors;

import com.example.library_management_platform.models.api.response.GetAllBooksResponse;
import com.example.library_management_platform.models.entities.Book;
import com.example.library_management_platform.models.entities.BookGenre;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookToConvertorBookDetails implements Converter<Book, GetAllBooksResponse.GetAllBooksData.BookDetails> {
    @Override
    public GetAllBooksResponse.GetAllBooksData.BookDetails convert(Book source) {

        List<String> genres  = new ArrayList<>();
        for(BookGenre genre: source.getBookGenres()){
            genres.add(genre.getName());
        }
        return new GetAllBooksResponse.GetAllBooksData.BookDetails(
                source.getId(),
                source.getName(),
                source.getAuthor(),
                genres,
                source.getPublisher()
        );
    }
}
