package com.example.library_management_platform.services;

import com.example.library_management_platform.convertors.BookEntityModelToBookDetailsConvertor;
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
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
@Slf4j
public class BookManagerService implements ItemManagerInterface<Long, GetAllBooksResponseModel.AllBookDetailsData.AllBookDetails, Object, Object,AddBookRequestModel,Object> {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookGenreRepository  bookGenreRepository;

    @Autowired
    BookEntityModelToBookDetailsConvertor bookEntityModelToBookDetailsConvertor;


    @Override
    public List<GetAllBooksResponseModel.AllBookDetailsData.AllBookDetails> getAllItems(Object searchRequestModel) {
        return null;
    }

    @Override
    public List<GetAllBooksResponseModel.AllBookDetailsData.AllBookDetails> getAllItemsWithoutSearchCriteria() {
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream().map(book -> bookEntityModelToBookDetailsConvertor.convert(book)).toList();
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
        for(BookGenre genre : bookGenreList){
            genre.getBooks().add(book);
            bookGenreRepository.save(genre);
        }
        return true;
    }

    @Override
    public Boolean removeItem(Long o) {
        return null;
    }

    @Override
    public Boolean updateItem( Object itemModel, Long id) {
        return null;
    }

}
