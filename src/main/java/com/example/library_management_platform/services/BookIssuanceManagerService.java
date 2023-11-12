package com.example.library_management_platform.services;

import com.example.library_management_platform.convertors.BookIssuanceToAllIssuanceObjConvertor;
import com.example.library_management_platform.convertors.CreateBookIssuanceModelToBookIssuanceConvertor;
import com.example.library_management_platform.models.api.request.CreateBookIssuanceRequestModel;
import com.example.library_management_platform.models.api.request.UpdateIssuanceRequestModel;
import com.example.library_management_platform.models.api.response.GetAllIssuanceResponseModel;
import com.example.library_management_platform.models.entities.Book;
import com.example.library_management_platform.models.entities.BookIssuance;
import com.example.library_management_platform.models.entities.Borrower;
import com.example.library_management_platform.models.entities.LibraryMembership;
import com.example.library_management_platform.repositories.BookIssuanceRepository;
import com.example.library_management_platform.repositories.BookRepository;
import com.example.library_management_platform.repositories.BorrowerRepository;
import com.example.library_management_platform.repositories.LibraryMembershipRepository;
import com.example.library_management_platform.services.interfaces.IssuanceManagerInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.library_management_platform.models.api.request.UpdateIssuanceRequestModel.BookIssuanceStatusEnum.rejected;
import static com.example.library_management_platform.models.api.request.UpdateIssuanceRequestModel.BookIssuanceStatusEnum.returned;
import static com.example.library_management_platform.models.entities.BookIssuance.StatusEnum.REJECTED;
import static com.example.library_management_platform.models.entities.BookIssuance.StatusEnum.RETURNED;


//BookIssuanceManagerService :- A service to manage Issuance
@Service
@Slf4j
public class BookIssuanceManagerService implements IssuanceManagerInterface<Long, CreateBookIssuanceRequestModel, UpdateIssuanceRequestModel, GetAllIssuanceResponseModel.AllIssuanceObj, BookIssuance.StatusEnum> {

    @Autowired
    BookIssuanceRepository bookIssuanceRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BorrowerRepository borrowerRepository;

    @Autowired
    CreateBookIssuanceModelToBookIssuanceConvertor createBookIssuanceModelToBookIssuanceConvertor;

    @Autowired
    BookIssuanceToAllIssuanceObjConvertor bookIssuanceToAllIssuanceObjConvertor;

    @Autowired
    LibraryMembershipRepository libraryMembershipRepository;

    @Override
    public Boolean createIssuance(CreateBookIssuanceRequestModel issuance) {
        BookIssuance bookIssuance = processCreateIssuance(issuance);
        if (bookIssuance == null) {
            log.error("BookIssuance creation failed!! payload: {}", issuance);
            return false;
        }

        Optional<Book> bookOpt = bookRepository.findById(bookIssuance.getBookId());
        Borrower borrower = bookIssuance.getBorrower();

        if (borrower.getLibraryMembership() == null || borrower.getLibraryMembership().getBorrower().getStatus() == Borrower.StatusEnum.IN_ACTIVE) {
            log.error("Membership not found!! payload : {}", issuance);
            bookIssuance.setStatus(REJECTED);
            bookIssuanceRepository.save(bookIssuance);
            bookOpt.get().setStatus(Book.StatusEnum.ACTIVE);
            bookRepository.save(bookOpt.get());
            return false;
        }

        if (borrower.getLibraryMembership().getExpiryDate().before(new Date())) {
            log.error("Membership expired!! payload: {}", issuance);
            LibraryMembership membership = borrower.getLibraryMembership();
            membership.setStatus(LibraryMembership.StatusEnum.IN_ACTIVE);
            libraryMembershipRepository.save(membership);
            bookIssuance.setStatus(REJECTED);
            bookIssuanceRepository.save(bookIssuance);
            bookOpt.get().setStatus(Book.StatusEnum.ACTIVE);
            bookRepository.save(bookOpt.get());
            return false;
        }

        if (borrower.getLibraryMembership().getBooksCount() == 5) {
            log.error("More than 5 books can't be issued at a time!! payload : {}", issuance);
            bookIssuance.setStatus(REJECTED);
            bookIssuanceRepository.save(bookIssuance);
            bookOpt.get().setStatus(Book.StatusEnum.ACTIVE);
            bookRepository.save(bookOpt.get());
            return false;
        }

        bookOpt.get().setStatus(Book.StatusEnum.IN_ACTIVE);
        bookRepository.save(bookOpt.get());
        bookIssuance.setStatus(BookIssuance.StatusEnum.APPROVED);
        bookIssuanceRepository.save(bookIssuance);
        LibraryMembership libraryMembership = borrower.getLibraryMembership();
        libraryMembership.setBooksCount(libraryMembership.getBooksCount() + 1);
        libraryMembershipRepository.save(libraryMembership);
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    private BookIssuance processCreateIssuance(CreateBookIssuanceRequestModel issuance) {
        Optional<Book> book = bookRepository.findById(issuance.getBookId());
        if (book.isEmpty() || book.get().getStatus() != Book.StatusEnum.ACTIVE) {
            throw new RuntimeException("Either bookId is invalid or book has already been issued");
        }
        Optional<Borrower> borrower = borrowerRepository.findById(issuance.getBorrowerId());
        if (borrower.isEmpty() || borrower.get().getStatus() != Borrower.StatusEnum.ACTIVE) {
            throw new RuntimeException("Either Borrower not found or Borrower is not Active");
        }

        BookIssuance bookIssuance = createBookIssuanceModelToBookIssuanceConvertor.convert(issuance);
        if (bookIssuance == null) {
            log.error("BookIssuanceManagerService, createIssuance returned null BookIssuance model");
            return null;
        }
        bookIssuanceRepository.save(bookIssuance);
        book.get().setStatus(Book.StatusEnum.ISSUANCE_REQUESTED);
        bookRepository.save(book.get());
        return bookIssuance;
    }

    @Override
    public Boolean updateIssuance(UpdateIssuanceRequestModel payload) {
        Optional<BookIssuance> bookIssuanceOpt = bookIssuanceRepository.findById(payload.getIssuanceId());
        if (bookIssuanceOpt.isEmpty()) {
            log.error("Issuance not found!! payload: {}", payload);
            return false;
        }
        if (List.of(rejected, returned).contains(payload.getStatus())) {
            Optional<Book> bookOpt = bookRepository.findById(bookIssuanceOpt.get().getBookId());
            if (bookOpt.isEmpty()) {
                log.error("Book not found!! payload:{}", payload);
                return false;
            }
            bookOpt.get().setStatus(Book.StatusEnum.ACTIVE);
            bookRepository.save(bookOpt.get());
            Borrower borrower = bookIssuanceOpt.get().getBorrower();
            LibraryMembership libraryMembership = borrower.getLibraryMembership();
            libraryMembership.setBooksCount(libraryMembership.getBooksCount() - 1);
            libraryMembershipRepository.save(libraryMembership);
            if (payload.getStatus() == rejected) {
                bookIssuanceOpt.get().setStatus(REJECTED);
            } else {
                bookIssuanceOpt.get().setStatus(RETURNED);
            }
            bookIssuanceRepository.save(bookIssuanceOpt.get());
            return true;
        } else {
            bookIssuanceOpt.get().setStatus(BookIssuance.StatusEnum.DELIVERED);
            bookIssuanceRepository.save(bookIssuanceOpt.get());
            return true;
        }
    }

    @Override
    public Boolean deleteIssuance(Long aLong) {
        return null;
    }

    public List<GetAllIssuanceResponseModel.AllIssuanceObj> getAllIssuance(BookIssuance.StatusEnum statusEnum) {
        List<BookIssuance> bookIssuanceList = bookIssuanceRepository.findAllByStatus(statusEnum);
        return bookIssuanceList.stream().map(bookIssuance -> bookIssuanceToAllIssuanceObjConvertor.convert(bookIssuance)).toList();
    }
}
