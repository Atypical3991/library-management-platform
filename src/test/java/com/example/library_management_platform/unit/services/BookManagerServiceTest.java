package com.example.library_management_platform.unit.services;


import com.example.library_management_platform.services.BookManagerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookManagerServiceTest {

    @Autowired
    BookManagerService bookManagerService;

    @Test
    public void getAllItemsSuccessTest(){};

    @Test
    public void addItemSuccessTest(){};


    @Test
    public void removeItemSuccessTest(){};

    @Test
    public void removeItemFailureTest(){};



}
