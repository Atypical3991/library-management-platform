package com.example.library_management_platform.services;


import com.example.library_management_platform.config.TestModels;
import com.example.library_management_platform.convertors.BookToAllBookDetailsConvertor;
import com.example.library_management_platform.models.api.request.AddBookRequestModel;
import com.example.library_management_platform.models.entities.Book;
import com.example.library_management_platform.models.entities.BookGenre;
import com.example.library_management_platform.repositories.BookGenreRepository;
import com.example.library_management_platform.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookGenreMangerServiceTest {


    @MockBean
    BookRepository bookRepositoryMock;

    @MockBean
    BookGenreRepository bookGenreRepositoryMock;

    @Autowired
    private BookManagerService bookManagerService;

    @Test
    public void addItemSuccessTest(){

        // Defining test models
        AddBookRequestModel addBookRequestModel = TestModels.addBookRequestModelTest;
        BookGenre bookGenre = TestModels.bookGenreTest;

        // Defining  method call behavior on mocks
        when(bookGenreRepositoryMock.findAllById(addBookRequestModel.getGenreIds())).thenReturn(List.of(bookGenre));
        when(bookRepositoryMock.save(any())).thenReturn(null);
        when(bookGenreRepositoryMock.save(any())).thenReturn(null);

        // Calling addItem method
        Boolean success = bookManagerService.addItem(addBookRequestModel);


        // Asserting results
        assertTrue(success,"addItem should return true");

        // Verifying mock method  calls
        Mockito.verify(bookRepositoryMock, Mockito.times(1)).save(any());
        Mockito.verify(bookGenreRepositoryMock, Mockito.times(1)).save(any());

    }


    @Test
    public void removeItemSuccessTest(){
        Long removeItemBookId  = 0L;

        // Defining method call behaviors on mocks
        doNothing().when(bookRepositoryMock).deleteById(removeItemBookId);

        // calling removeItem method
        bookManagerService.removeItem(removeItemBookId);

        // Verifying mock method calls
        Mockito.verify(bookRepositoryMock, Mockito.times(1)).deleteById(removeItemBookId);
    }


    @Test
    public void removeItemFailureTest(){
        Long removeItemBookId  = 0L;

        // Defining method call behaviors on mocks
        doThrow(new RuntimeException("Something went wrong")).when(bookRepositoryMock).deleteById(removeItemBookId);

        // Calling removeItem method
        Boolean success = bookManagerService.removeItem(removeItemBookId);

        // Asserting false response
        assertFalse(success,"removeItem should return false");

        // Verifying mock method calls
        Mockito.verify(bookRepositoryMock, Mockito.times(1)).deleteById(removeItemBookId);

    }

}
