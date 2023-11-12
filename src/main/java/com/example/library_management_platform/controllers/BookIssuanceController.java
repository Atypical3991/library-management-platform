package com.example.library_management_platform.controllers;

import com.example.library_management_platform.convertors.CreateBookIssuanceModelToBookIssuanceConvertor;
import com.example.library_management_platform.models.api.request.CreateBookIssuanceRequestModel;
import com.example.library_management_platform.models.api.request.UpdateIssuanceRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.models.api.response.GetAllIssuanceResponseModel;
import com.example.library_management_platform.models.entities.Book;
import com.example.library_management_platform.models.entities.BookIssuance;
import com.example.library_management_platform.models.entities.Borrower;
import com.example.library_management_platform.models.entities.LibraryMembership;
import com.example.library_management_platform.repositories.BookIssuanceRepository;
import com.example.library_management_platform.repositories.BookRepository;
import com.example.library_management_platform.repositories.LibraryMembershipRepository;
import com.example.library_management_platform.services.BookIssuanceManagerService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.library_management_platform.models.api.request.UpdateIssuanceRequestModel.BookIssuanceStatusEnum.rejected;
import static com.example.library_management_platform.models.api.request.UpdateIssuanceRequestModel.BookIssuanceStatusEnum.returned;
import static com.example.library_management_platform.models.entities.BookIssuance.StatusEnum.REJECTED;
import static com.example.library_management_platform.models.entities.BookIssuance.StatusEnum.RETURNED;


@RestController
@RequestMapping("/api/issuance")
@Slf4j
public class BookIssuanceController {


    @Autowired
    BookIssuanceManagerService bookIssuanceManagerService;

    @Autowired
    BookIssuanceRepository bookIssuanceRepository;

    @Autowired
    LibraryMembershipRepository libraryMembershipRepository;

    @Autowired
    BookRepository bookRepository;


    @Autowired
    CreateBookIssuanceModelToBookIssuanceConvertor createBookIssuanceModelToBookIssuanceConvertor;

    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "200", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class)))
    })
    public ResponseEntity<BaseResponseModel> issueBook(@RequestBody @Valid CreateBookIssuanceRequestModel payload, @RequestHeader String Authorization) {
        try {
            BookIssuance bookIssuance = bookIssuanceManagerService.createIssuance(payload);

            if (bookIssuance == null) {
                throw new RuntimeException("Something went wrong");
            }

            Optional<Book> bookOpt = bookRepository.findById(bookIssuance.getBookId());
            Borrower borrower = bookIssuance.getBorrower();

            if (borrower.getLibraryMembership() == null || borrower.getLibraryMembership().getBorrower().getStatus() == Borrower.StatusEnum.IN_ACTIVE) {
                bookIssuance.setStatus(REJECTED);
                bookIssuanceRepository.save(bookIssuance);
                bookOpt.get().setStatus(Book.StatusEnum.ACTIVE);
                bookRepository.save(bookOpt.get());
                return ResponseEntity.badRequest().body(new BaseResponseModel(false, "Membership not found!!", null));
            }

            if (borrower.getLibraryMembership().getExpiryDate().before(new Date())) {
                LibraryMembership membership = borrower.getLibraryMembership();
                membership.setStatus(LibraryMembership.StatusEnum.IN_ACTIVE);
                libraryMembershipRepository.save(membership);
                bookIssuance.setStatus(REJECTED);
                bookIssuanceRepository.save(bookIssuance);
                bookOpt.get().setStatus(Book.StatusEnum.ACTIVE);
                bookRepository.save(bookOpt.get());
                return ResponseEntity.badRequest().body(new BaseResponseModel(false, "Membership expired!!", null));
            }

            if (borrower.getLibraryMembership().getBooksCount() == 5) {
                bookIssuance.setStatus(REJECTED);
                bookIssuanceRepository.save(bookIssuance);
                bookOpt.get().setStatus(Book.StatusEnum.ACTIVE);
                bookRepository.save(bookOpt.get());
                return ResponseEntity.badRequest().body(new BaseResponseModel(false, "More than 5 books can't be issued at a time!!", null));
            }

            bookOpt.get().setStatus(Book.StatusEnum.IN_ACTIVE);
            bookRepository.save(bookOpt.get());
            bookIssuance.setStatus(BookIssuance.StatusEnum.APPROVED);
            bookIssuanceRepository.save(bookIssuance);
            LibraryMembership libraryMembership = borrower.getLibraryMembership();
            libraryMembership.setBooksCount(libraryMembership.getBooksCount() + 1);
            libraryMembershipRepository.save(libraryMembership);

            return ResponseEntity.ok().body(new BaseResponseModel(true, null, "Book issued successfully"));

        } catch (Exception e) {
            log.error("BookIssuanceController, issueBook exception raised!! payload: {}", payload, e);
            return ResponseEntity.internalServerError().body(new BaseResponseModel(false, "Something went wrong!!", null));
        }
    }

    @PutMapping("/update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "200", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class)))
    })
    public ResponseEntity<BaseResponseModel> updateIssuance(@RequestBody @Valid UpdateIssuanceRequestModel payload, @RequestHeader String Authorization) {
        try {
            Optional<BookIssuance> bookIssuanceOpt = bookIssuanceRepository.findById(payload.getIssuanceId());
            if (bookIssuanceOpt.isEmpty()) {
                throw new RuntimeException("Issuance not found!!");
            }
            if (List.of(rejected, returned).contains(payload.getStatus())) {
                Optional<Book> bookOpt = bookRepository.findById(bookIssuanceOpt.get().getBookId());
                if (bookOpt.isEmpty()) {
                    throw new RuntimeException("Oops!! Something went wrong");
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
            } else {
                bookIssuanceOpt.get().setStatus(BookIssuance.StatusEnum.DELIVERED);
                bookIssuanceRepository.save(bookIssuanceOpt.get());
            }
            return ResponseEntity.ok().body(new BaseResponseModel(true, null, "Successfully updated."));
        } catch (Exception e) {
            log.error("BookIssuanceController, updateIssuance exception raised!! payload: {}", payload, e);
            return ResponseEntity.internalServerError().body(new BaseResponseModel(false, "Something went wrong!!", null));
        }
    }

    @GetMapping("/all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetAllIssuanceResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetAllIssuanceResponseModel.class)))
    })
    public ResponseEntity<GetAllIssuanceResponseModel> getAllActiveIssuance(@RequestParam BookIssuance.StatusEnum statusEnum, @RequestHeader String Authorization) {
        try {
            return ResponseEntity.ok().body(new GetAllIssuanceResponseModel(true, null, null, new GetAllIssuanceResponseModel.AllIssuanceDetailsData(bookIssuanceManagerService.getAllIssuance(statusEnum))));
        } catch (Exception e) {
            log.error("BookIssuanceController, getAllActiveIssuance exception raised!!", e);
            return ResponseEntity.internalServerError().body(new GetAllIssuanceResponseModel(false, "Something went wrong!!", null, null));
        }
    }
}
