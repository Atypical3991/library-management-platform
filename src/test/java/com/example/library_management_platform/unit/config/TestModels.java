package com.example.library_management_platform.unit.config;

import com.example.library_management_platform.models.api.request.AddBookRequestModel;
import com.example.library_management_platform.models.api.request.CreateBookIssuanceRequestModel;
import com.example.library_management_platform.models.entities.Book;
import com.example.library_management_platform.models.entities.BookGenre;
import com.example.library_management_platform.models.entities.Borrower;
import com.example.library_management_platform.models.entities.LibraryMembership;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Component
public  class TestModels {

    public static AddBookRequestModel addBookRequestModelTest = new AddBookRequestModel(
            "testBook",
            new HashSet(List.of(-1)),
            "testAuthor",
            "testPublisher"
    );

    public static BookGenre bookGenreTest = new BookGenre(
            "testGenre",
            "test-genre",
            new ArrayList<>()
    );

    public static CreateBookIssuanceRequestModel createBookIssuanceRequestModelTest = new CreateBookIssuanceRequestModel(
            0L,
            0L,
            new SimpleDateFormat("dd/MM/yyyy").format(new Date()),
            new SimpleDateFormat("dd/MM/yyyy").format(Date.from(LocalDate.now().plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant()))
    );

    public static Book bookTest =  new Book(
            "testBook",
            "testSlug",
            "testAuthor",
            new HashSet<>(List.of(bookGenreTest)),
            Book.StatusEnum.ACTIVE,
            "testPublisher"
    );

    public static Borrower borrowerTest = new Borrower(
            "testUsername",
            "testContactEmail",
            "testContactNumber",
            "testPassword",
            "testFirstName",
            "testLastName",
            Borrower.GenderEnum.MALE,
            "13/01/1993",
            new ArrayList<>(),
            Borrower.StatusEnum.ACTIVE,
            "testAddress",
            null
    ) ;
    public static LibraryMembership libraryMembershipTest =  new LibraryMembership(
            borrowerTest,
            new Date(),
            Date.from(LocalDate.now().plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant()),
            LibraryMembership.StatusEnum.ACTIVE,
            0
    );

}