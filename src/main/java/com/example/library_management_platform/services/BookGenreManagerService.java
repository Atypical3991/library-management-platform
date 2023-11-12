package com.example.library_management_platform.services;

import com.example.library_management_platform.convertors.AddBookGenreRequestToBookGenreEntityModelConvertor;
import com.example.library_management_platform.convertors.BookGenreEntityModelToBookGenreDetailsConvertor;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookGenreManagerService implements ItemManagerInterface<Long, GetAllBookGenresResponseModel.AllGenreObj, GetBookGenreByIdResponseModel.BookGenreByIdDetails, UpdateBookGenreRequestModel, AddBookGenreRequestModel> {

    @Autowired
    AddBookGenreRequestToBookGenreEntityModelConvertor addBookGenreRequestToBookGenreEntityModelConvertor;

    @Autowired
    BookGenreEntityModelToBookGenreDetailsConvertor bookGenreEntityModelToBookGenreDetailsConvertor;

    @Autowired
    BookGenreRepository bookGenreRepository;

    @Override
    public List<GetAllBookGenresResponseModel.AllGenreObj> getAllItems(Pageable pageable) {
        Sort sort = Sort.by(Sort.Order.asc("createdAt"));
        int page = 0;
        int size = 20;
        long count = bookGenreRepository.count();
        List<BookGenre> genresList = new ArrayList<>();
        while (((long) page * size) < count) {
            PageRequest pageRequest = PageRequest.of(page, size, sort);
            List<BookGenre> bookGenreListByPage = bookGenreRepository.findAll(pageRequest).getContent();
            genresList.addAll(bookGenreListByPage);
            page += 1;
        }
        return genresList.stream().map(genre -> new GetAllBookGenresResponseModel.AllGenreObj(genre.getId(), genre.getName())).toList();
    }

    @Override
    public GetBookGenreByIdResponseModel.BookGenreByIdDetails getItemById(Long id) {
        Optional<BookGenre> genreObj = bookGenreRepository.findById(id);
        if (genreObj.isEmpty()) {
            throw new RuntimeException("No genre found");
        }
        return bookGenreEntityModelToBookGenreDetailsConvertor.convert(genreObj.get());
    }

    @Override
    public Boolean addItem(AddBookGenreRequestModel itemModel) {
        BookGenre bookGenre = addBookGenreRequestToBookGenreEntityModelConvertor.convert(itemModel);
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
