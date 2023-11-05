package com.example.library_management_platform.controllers;

import com.example.library_management_platform.models.api.request.AddBookRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.models.api.response.GetAllBooksResponseModel;
import com.example.library_management_platform.services.BookManagerService;
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
    public BaseResponseModel addBook(@RequestBody @Valid AddBookRequestModel payload, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new BaseResponseModel(false, String.join(", ", errors), "");
        }
        try {
            Boolean success = bookManagerService.addItem(payload);
            return new BaseResponseModel(success, null, "Book added successfully");
        } catch (Exception e) {
            log.error("BookController, addBook exception raised!! payload: {}", payload, e);
            return new BaseResponseModel(false, "Something went wrong.", null);
        }
    }

    @GetMapping("/all")
    public BaseResponseModel getAllBooks(){
        try {
            List<GetAllBooksResponseModel.DataObj.BookDetails> bookDetailsList = bookManagerService.getAllItemsWithoutSearchCriteria();
            return new GetAllBooksResponseModel(true, null, "Book added successfully",new GetAllBooksResponseModel.DataObj(bookDetailsList.size(),bookDetailsList) );
        } catch (Exception e) {
            log.error("BookController, addBook exception raised!!", e);
            return new GetAllBooksResponseModel(false, "Something went wrong.", null,null);
        }
    }
}
