package com.example.library_management_platform.services;

import com.example.library_management_platform.convertors.BookIssuanceEntityModelToIssuanceObjConvertor;
import com.example.library_management_platform.convertors.CreateBookIssuanceModelToBookIssuanceEntityModelConvertor;
import com.example.library_management_platform.models.api.request.CreateBookIssuanceModel;
import com.example.library_management_platform.models.api.response.GetAllIssuanceResponseModel;
import com.example.library_management_platform.models.entities.Book;
import com.example.library_management_platform.models.entities.BookIssuance;
import com.example.library_management_platform.models.entities.Borrower;
import com.example.library_management_platform.repositories.BookIssuanceRepository;
import com.example.library_management_platform.repositories.BookRepository;
import com.example.library_management_platform.repositories.BorrowerRepository;
import com.example.library_management_platform.services.interfaces.IssuanceManagerInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class BookIssuanceManagerService implements IssuanceManagerInterface<Long, CreateBookIssuanceModel,Object, GetAllIssuanceResponseModel.AllIssuanceObj, BookIssuance.StatusEnum, BookIssuance> {

    @Autowired
    BookIssuanceRepository bookIssuanceRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BorrowerRepository borrowerRepository;

    @Autowired
    CreateBookIssuanceModelToBookIssuanceEntityModelConvertor createBookIssuanceModelToBookIssuanceEntityModelConvertor;

    @Autowired
    BookIssuanceEntityModelToIssuanceObjConvertor bookIssuanceEntityModelToIssuanceObjConvertor;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.REPEATABLE_READ)
    public BookIssuance createIssuance(CreateBookIssuanceModel issuance) {
        Optional<Book> book = bookRepository.findById(issuance.getBookId());
        if(book.isEmpty() || book.get().getStatus() != Book.StatusEnum.ACTIVE){
            throw new RuntimeException("Either bookId is invalid or book has already been issued");
        }
        Optional<Borrower> borrower = borrowerRepository.findById(issuance.getBorrowerId());
        if(borrower.isEmpty() || borrower.get().getStatus() != Borrower.StatusEnum.ACTIVE){
            throw new RuntimeException("Either Borrower not found or Borrower is not Active");
        }

        BookIssuance bookIssuance = createBookIssuanceModelToBookIssuanceEntityModelConvertor.convert(issuance);
        if(bookIssuance == null){
            log.error("BookIssuanceManagerService, createIssuance returned null BookIssuance model");
            return null;
        }
        bookIssuanceRepository.save(bookIssuance);
        book.get().setStatus(Book.StatusEnum.ISSUANCE_REQUESTED);
        bookRepository.save(book.get());
        return bookIssuance;

    }

    @Override
    public Boolean updateIssuance(Object issuance) {
        return null;
    }

    @Override
    public Boolean deleteIssuance(Long aLong) {
        return null;
    }

    public List<GetAllIssuanceResponseModel.AllIssuanceObj> getAllIssuance(BookIssuance.StatusEnum statusEnum ){
        List<BookIssuance> bookIssuanceList = bookIssuanceRepository.findAllByStatus(statusEnum);
        return bookIssuanceList.stream().map(bookIssuance -> bookIssuanceEntityModelToIssuanceObjConvertor.convert(bookIssuance)).toList();
    }
}
