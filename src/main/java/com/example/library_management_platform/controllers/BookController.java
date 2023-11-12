package com.example.library_management_platform.controllers;

import com.example.library_management_platform.models.api.request.AddBookRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.models.api.response.GetAllBooksResponseModel;
import com.example.library_management_platform.services.BookManagerService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class)))
    })
    public ResponseEntity<BaseResponseModel> addBook(@RequestBody @Valid AddBookRequestModel payload, @RequestHeader String Authorization, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(new BaseResponseModel(false, String.join(", ", errors), ""));
        }
        try {
            Boolean success = bookManagerService.addItem(payload);
            return ResponseEntity.ok().body(new BaseResponseModel(success, null, "Book added successfully"));
        } catch (Exception e) {
            log.error("BookController, addBook exception raised!! payload: {}", payload, e);
            return ResponseEntity.internalServerError().body(new BaseResponseModel(false, "Something went wrong.", null));
        }
    }

    @GetMapping("/all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetAllBooksResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetAllBooksResponseModel.class)))
    })
    public ResponseEntity<Page<GetAllBooksResponseModel.AllBookDetailsData.AllBookDetails>> getAllBooks(Pageable pageable) {
        try {
            Page<GetAllBooksResponseModel.AllBookDetailsData.AllBookDetails> allBookDetailsList = bookManagerService.getAllItems(pageable);
            return ResponseEntity.ok().body(allBookDetailsList);
        } catch (Exception e) {
            log.error("BookController, getAllBooks exception raised!!", e);
            return ResponseEntity.internalServerError().body(null);
        }
    }

    //TODO: add API for fetching single book details by Id
    //TODO: add API for fetching books by Genre.

    @DeleteMapping("/delete/{bookId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class)))
    })
    public ResponseEntity<BaseResponseModel> removeBookById(@PathVariable long bookId, @RequestHeader String Authorization) {
        try {
            Boolean success = bookManagerService.removeItem(bookId);
            return ResponseEntity.ok().body(new BaseResponseModel(success, "book removed successfully", null));
        } catch (Exception e) {
            log.error("BookController, removeBookById exception raised!! bookId : {} ", bookId, e);
            return ResponseEntity.internalServerError().body(new BaseResponseModel(false, "something went wrong", null));
        }
    }
}
