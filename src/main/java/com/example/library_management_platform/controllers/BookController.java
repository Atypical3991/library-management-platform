package com.example.library_management_platform.controllers;

import com.example.library_management_platform.models.api.request.AddBookRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponse;
import com.example.library_management_platform.models.api.response.GetAllBooksResponse;
import com.example.library_management_platform.services.BookManagerService;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Slf4j
public class BookController {

    @Autowired
    BookManagerService bookManagerService;

    @PostMapping("")
    public BaseResponse addBook(@RequestBody @Valid AddBookRequestModel payload, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new BaseResponse(false, String.join(", ", errors), "");
        }
        try {
            Boolean success = bookManagerService.addItem(payload);
            return new BaseResponse(success, null, "Book added successfully");
        } catch (Exception e) {
            log.error("BookController, addBook exception raised!! payload: {}", payload, e);
            return new BaseResponse(false, "Something went wrong.", null);
        }
    }

    @GetMapping("/all")
    public BaseResponse getAllBooks(){
        try {
            List<GetAllBooksResponse.GetAllBooksData.BookDetails> bookDetailsList = bookManagerService.getAllItemsWithoutSearchCriteria();
            return new GetAllBooksResponse(true, null, "Book added successfully",new GetAllBooksResponse.GetAllBooksData(bookDetailsList.size(),bookDetailsList) );
        } catch (Exception e) {
            log.error("BookController, addBook exception raised!!", e);
            return new BaseResponse(false, "Something went wrong.", null);
        }
    }
}
