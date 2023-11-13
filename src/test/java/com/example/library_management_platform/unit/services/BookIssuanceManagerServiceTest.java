package com.example.library_management_platform.unit.services;

import com.example.library_management_platform.convertors.BookIssuanceToAllIssuanceObjConvertor;
import com.example.library_management_platform.convertors.CreateBookIssuanceModelToBookIssuanceConvertor;
import com.example.library_management_platform.models.entities.Borrower;
import com.example.library_management_platform.repositories.BookIssuanceRepository;
import com.example.library_management_platform.repositories.BookRepository;
import com.example.library_management_platform.repositories.BorrowerRepository;
import com.example.library_management_platform.repositories.LibraryMembershipRepository;
import com.example.library_management_platform.services.BookIssuanceManagerService;
import com.example.library_management_platform.unit.config.TestModels;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookIssuanceManagerServiceTest {

    @Autowired
    BookIssuanceManagerService bookIssuanceManagerService;


    @MockBean
    BorrowerRepository borrowerRepositoryMock;

    @MockBean
    BookRepository bookRepositoryMock;

    @MockBean
    BookIssuanceRepository bookIssuanceRepositoryMock;

    @MockBean
    LibraryMembershipRepository libraryMembershipRepository;


    @Test
    public void createIssuanceSuccessTest(){

        // Defining test variables
        Borrower borrowerTest = TestModels.borrowerTest;
        borrowerTest.setLibraryMembership(TestModels.libraryMembershipTest);

        // Defining method behaviour of mocks
        when(bookRepositoryMock.findById(TestModels.createBookIssuanceRequestModelTest.getBookId())).thenReturn(Optional.ofNullable(TestModels.bookTest));
        when(borrowerRepositoryMock.findById(TestModels.createBookIssuanceRequestModelTest.getBorrowerId())).thenReturn(Optional.ofNullable(TestModels.borrowerTest));
        when(bookRepositoryMock.save(any())).thenReturn(null);
        when(bookIssuanceRepositoryMock.save(any())).thenReturn(null);
        when(libraryMembershipRepository.save(any())).thenReturn(null);

        // Calling createIssuance method
        bookIssuanceManagerService.createIssuance(TestModels.createBookIssuanceRequestModelTest);

        // Verifying mock method  calls
        Mockito.verify(bookRepositoryMock, Mockito.times(2)).save(any());
        Mockito.verify(bookIssuanceRepositoryMock, Mockito.times(2)).save(any());
        Mockito.verify(libraryMembershipRepository, Mockito.times(1)).save(any());
    };

    @Test
    public void createIssuanceMembershipNotFoundTest(){

        // Defining test variables
        Borrower borrowerTest = TestModels.borrowerTest;

        // Defining method behaviour of mocks
        when(bookRepositoryMock.findById(TestModels.createBookIssuanceRequestModelTest.getBookId())).thenReturn(Optional.ofNullable(TestModels.bookTest));
        when(borrowerRepositoryMock.findById(TestModels.createBookIssuanceRequestModelTest.getBorrowerId())).thenReturn(Optional.ofNullable(TestModels.borrowerTest));
        when(bookRepositoryMock.save(any())).thenReturn(null);
        when(bookIssuanceRepositoryMock.save(any())).thenReturn(null);
        when(libraryMembershipRepository.save(any())).thenReturn(null);

        // Calling createIssuance method
        bookIssuanceManagerService.createIssuance(TestModels.createBookIssuanceRequestModelTest);

        // Verifying mock method  calls
        Mockito.verify(bookRepositoryMock, Mockito.times(2)).save(any());
        Mockito.verify(bookIssuanceRepositoryMock, Mockito.times(2)).save(any());
        Mockito.verify(libraryMembershipRepository, Mockito.times(1)).save(any());

    };

    @Test
    public void createIssuanceMembershipExpiredTest(){};

    @Test
    public  void createIssuanceExceededMaxNumberOfIssuanceTest(){};


    @Test
    public void createIssuanceFailureTest(){};

    @Test
    public void updateIssuanceSuccessTest(){};


    @Test
    public void updateIssuanceBookNotFoundTest(){};

    @Test
    public void updateIssuanceBookIssuanceNotFoundTest(){};


    @Test
    public void getAllIssuanceSuccessTest(){};

}
