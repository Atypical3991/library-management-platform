package com.example.library_management_platform.convertors;

import com.example.library_management_platform.models.api.response.GetAllIssuanceResponseModel;
import com.example.library_management_platform.models.entities.Book;
import com.example.library_management_platform.models.entities.BookIssuance;
import com.example.library_management_platform.repositories.BookRepository;
import com.example.library_management_platform.repositories.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookIssuanceToAllIssuanceObjConvertor implements Converter<BookIssuance, GetAllIssuanceResponseModel.AllIssuanceObj> {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BorrowerRepository borrowerRepository;


    @Override
    public GetAllIssuanceResponseModel.AllIssuanceObj convert(BookIssuance source) {
        Optional<Book> book = bookRepository.findById(source.getBookId());
        return book.map(value -> new GetAllIssuanceResponseModel.AllIssuanceObj(
                value.getName(),
                source.getBorrower().getUsername(),
                source.getStatus().name().toLowerCase(),
                source.getExpiredAt().toString(),
                source.getIssuedAt().toString()
        )).orElse(null);
    }
}
