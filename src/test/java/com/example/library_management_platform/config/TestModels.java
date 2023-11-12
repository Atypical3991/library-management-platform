package com.example.library_management_platform.config;

import com.example.library_management_platform.models.api.request.AddBookRequestModel;
import com.example.library_management_platform.models.entities.Book;
import com.example.library_management_platform.models.entities.BookGenre;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Component
public  class TestModels {

    public  static AddBookRequestModel addBookRequestModelTest =  new AddBookRequestModel(
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


}