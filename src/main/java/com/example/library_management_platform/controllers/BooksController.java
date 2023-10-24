package com.example.library_management_platform.controllers;

import com.example.library_management_platform.models.api.request.AddBookWithDetailsRequest;
import com.example.library_management_platform.models.api.response.BaseResponse;
import com.example.library_management_platform.models.api.response.GetAllBooksResponse;
import com.example.library_management_platform.models.api.response.GetBookDetailsResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/books")
public class BooksController {


    /**
     * getAllBooks :- returns list of available books based on search params such as genreId,authorId and publisherId.
     * @param genreId (id of Genre table)
     * @param authorId (id of author table)
     * @param publisherId (id of publisher table)
     * @return GetAllBooksResponse (contains list of available books)
     */
    @GetMapping("/")
    public GetAllBooksResponse getAllBooks(
            @RequestParam(required = false) Long genreId,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Long publisherId
            ){};

    /**
     * getBookDetailById :- returns book details based on book id.
     * @param bookId (id of book table)
     * @return GetBookDetailsResponse (contains details of book associated with value of bookId param from book table)
     */
    @GetMapping("/{bookId}")
    public GetBookDetailsResponse getBookDetailById(@PathVariable Long bookId){};


    /**
     * addBookWithDetails :- to add a new book into the library inventory.
     * @param body (contains book table related details)
     * @return BaseResponse (base response)
     */
    @PostMapping("/add")
    public BaseResponse addBookWithDetails(@RequestBody AddBookWithDetailsRequest body){};


    /**
     * removeBookById:- to remove a book from the library inventory.
     * @param bookId (id of book table)
     * @return BaseResponse (base response)
     */
    @DeleteMapping("/remove/{bookId}")
    public BaseResponse removeBookById(@PathVariable Long bookId){};

}
