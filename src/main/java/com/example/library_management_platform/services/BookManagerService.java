package com.example.library_management_platform.services;

import com.example.library_management_platform.convertors.BookToAllBookDetailsConvertor;
import com.example.library_management_platform.models.api.request.AddBookRequestModel;
import com.example.library_management_platform.models.api.response.GetAllBooksResponseModel;
import com.example.library_management_platform.models.entities.Book;
import com.example.library_management_platform.models.entities.BookGenre;
import com.example.library_management_platform.repositories.BookGenreRepository;
import com.example.library_management_platform.repositories.BookRepository;
import com.example.library_management_platform.services.interfaces.ItemManagerInterface;
import com.example.library_management_platform.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
@Slf4j
public class BookManagerService implements ItemManagerInterface<Long, GetAllBooksResponseModel.AllBookDetailsData.AllBookDetails, Object, Object, AddBookRequestModel> {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookGenreRepository bookGenreRepository;

    @Autowired
    BookToAllBookDetailsConvertor bookToAllBookDetailsConvertor;

    @Override
    public Page<GetAllBooksResponseModel.AllBookDetailsData.AllBookDetails> getAllItems(Pageable pageable) {
        Page<Book> bookList = bookRepository.findAll((org.springframework.data.domain.Pageable) pageable);
        List<GetAllBooksResponseModel.AllBookDetailsData.AllBookDetails> bookDetailsList = bookList.stream().map(book -> bookToAllBookDetailsConvertor.convert(book)).toList();
        return new PageImpl<>(bookDetailsList, (org.springframework.data.domain.Pageable) pageable, bookDetailsList.size());

    }

    @Override
    public Object getItemById(Long aLong) {
        return null;
    }

    @Override
    public Boolean addItem(AddBookRequestModel addBookRequestModel) {
        List<BookGenre> bookGenreList = bookGenreRepository.findAllById(addBookRequestModel.getGenreIds());
        Book book = new Book();
        book.setName(addBookRequestModel.getName());
        book.setSlug(StringUtil.convertToSlug(addBookRequestModel.getName()));
        book.setAuthor(addBookRequestModel.getAuthor());
        book.setPublisher(addBookRequestModel.getPublisher());
        book.setStatus(Book.StatusEnum.ACTIVE);
        book.setBookGenres(new HashSet<>(bookGenreList));
        bookRepository.save(book);
        for (BookGenre genre : bookGenreList) {
            genre.getBooks().add(book);
            bookGenreRepository.save(genre);
        }
        return true;
    }

    @Override
    public Boolean removeItem(Long bookId) {
        try {
            bookRepository.deleteById(bookId);
            return true;
        } catch (Exception e) {
            log.error("BookManagerService, removeItem exception raised!! bookId : {}", bookId, e);
            return false;
        }

    }

    @Override
    public Boolean updateItem(Object itemModel, Long id) {
        return null;
    }

}
