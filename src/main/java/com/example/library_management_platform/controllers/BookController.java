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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<BaseResponseModel> addBook(@RequestBody @Valid AddBookRequestModel payload, @RequestHeader String Authorization) {
        Boolean success = bookManagerService.addItem(payload);
        if (!success) {
            return ResponseEntity.ok().body(new BaseResponseModel(false, "Something went wrong", null));
        }
        return ResponseEntity.ok().body(new BaseResponseModel(true, null, "Book added successfully"));
    }

    @GetMapping("/all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetAllBooksResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetAllBooksResponseModel.class)))
    })
    public ResponseEntity<Page<GetAllBooksResponseModel.AllBookDetailsData.AllBookDetails>> getAllBooks(Pageable pageable) {
        Page<GetAllBooksResponseModel.AllBookDetailsData.AllBookDetails> allBookDetailsList = bookManagerService.getAllItems(pageable);
        return ResponseEntity.ok().body(allBookDetailsList);
    }

    //TODO: add API for fetching single book details by Id
    //TODO: add API for fetching books by Genre.

    @DeleteMapping("/delete/{bookId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class)))
    })
    public ResponseEntity<BaseResponseModel> removeBookById(@PathVariable long bookId, @RequestHeader String Authorization) {
        Boolean success = bookManagerService.removeItem(bookId);
        if (!success) {
            return ResponseEntity.ok().body(new BaseResponseModel(false, "Something went wrong", null));
        }
        return ResponseEntity.ok().body(new BaseResponseModel(true, null, "book removed successfully"));
    }
}
