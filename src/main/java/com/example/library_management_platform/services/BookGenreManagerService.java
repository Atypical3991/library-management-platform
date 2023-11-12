package com.example.library_management_platform.services;

import com.example.library_management_platform.convertors.AddBookGenreRequestModelToBookGenreConvertor;
import com.example.library_management_platform.convertors.BookGenreToBookGenreDetailsConvertor;
import com.example.library_management_platform.models.api.request.AddBookGenreRequestModel;
import com.example.library_management_platform.models.api.request.UpdateBookGenreRequestModel;
import com.example.library_management_platform.models.api.response.GetAllBookGenresResponseModel;
import com.example.library_management_platform.models.api.response.GetBookGenreByIdResponseModel;
import com.example.library_management_platform.models.entities.BookGenre;
import com.example.library_management_platform.repositories.BookGenreRepository;
import com.example.library_management_platform.services.interfaces.ItemManagerInterface;
import com.example.library_management_platform.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BookGenreManagerService implements ItemManagerInterface<Long, GetAllBookGenresResponseModel.AllGenreObj, GetBookGenreByIdResponseModel.BookGenreByIdDetails, UpdateBookGenreRequestModel, AddBookGenreRequestModel> {

    @Autowired
    AddBookGenreRequestModelToBookGenreConvertor addBookGenreRequestModelToBookGenreConvertor;

    @Autowired
    BookGenreToBookGenreDetailsConvertor bookGenreToBookGenreDetailsConvertor;

    @Autowired
    BookGenreRepository bookGenreRepository;

    @Override
    public Page<GetAllBookGenresResponseModel.AllGenreObj> getAllItems(Pageable pageable) {
        return null;
    }

    @Override
    public GetBookGenreByIdResponseModel.BookGenreByIdDetails getItemById(Long id) {
        Optional<BookGenre> genreObj = bookGenreRepository.findById(id);
        if (genreObj.isEmpty()) {
            throw new RuntimeException("No genre found");
        }
        return bookGenreToBookGenreDetailsConvertor.convert(genreObj.get());
    }

    @Override
    public Boolean addItem(AddBookGenreRequestModel itemModel) {
        BookGenre bookGenre = addBookGenreRequestModelToBookGenreConvertor.convert(itemModel);
        if (bookGenre == null) {
            log.error("BookManagerService, addGenre null type genre can't be processed, payload: {}", itemModel);
            throw new RuntimeException("null type genre can't be processed.");
        }
        bookGenreRepository.save(bookGenre);
        return true;
    }

    @Override
    public Boolean removeItem(Long id) {
        bookGenreRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean updateItem(UpdateBookGenreRequestModel genreUpdateModel, Long id) {
        Optional<BookGenre> genre = bookGenreRepository.findById(id);
        if (genre.isEmpty()) {
            throw new RuntimeException("Genre not found.");
        }
        BookGenre bookGenreObj = genre.get();
        if (genreUpdateModel.getName() != null) {
            bookGenreObj.setName(genreUpdateModel.getName());
            bookGenreObj.setSlug(StringUtil.convertToSlug(genreUpdateModel.getName()));
        }
        bookGenreRepository.save(bookGenreObj);
        return true;
    }
}
