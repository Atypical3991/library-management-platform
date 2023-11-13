package com.example.library_management_platform.controllers;

import com.example.library_management_platform.models.api.request.AddBookGenreRequestModel;
import com.example.library_management_platform.models.api.request.UpdateBookGenreRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.models.api.response.GetBookGenreByIdResponseModel;
import com.example.library_management_platform.services.BookGenreManagerService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/genres")
@Slf4j
public class BookGenreController {

    @Autowired
    BookGenreManagerService bookGenreManagerService;

    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class)))
    })
    public ResponseEntity<BaseResponseModel> addGenre(@RequestBody @Valid AddBookGenreRequestModel addBookGenreRequestModelPayload, @RequestHeader String Authorization) {
        Boolean success = bookGenreManagerService.addItem(addBookGenreRequestModelPayload);
        if (!success) {
            return ResponseEntity.internalServerError().body(new BaseResponseModel(false, "Genre addition failed.", null));
        }
        return ResponseEntity.ok().body(new BaseResponseModel(true, null, "Genre added successfully."));

    }

    @GetMapping("/{genreId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetBookGenreByIdResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetBookGenreByIdResponseModel.class)))
    })
    public ResponseEntity<GetBookGenreByIdResponseModel> getGenreByID(@PathVariable long genreId) {
        GetBookGenreByIdResponseModel.BookGenreByIdDetails bookGenreByIdDetails = bookGenreManagerService.getItemById(genreId);
        return ResponseEntity.ok().body(new GetBookGenreByIdResponseModel(true, null, "Genre details fetched successfully.", new GetBookGenreByIdResponseModel.BookGenreByIdDetailsData(bookGenreByIdDetails)));
    }

    @DeleteMapping("/delete/{genreId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class)))
    })
    public ResponseEntity<BaseResponseModel> removeGenreById(@PathVariable long genreId, @RequestHeader String Authorization) {
        Boolean success = bookGenreManagerService.removeItem(genreId);
        if (!success) {
            return ResponseEntity.ok().body(new BaseResponseModel(false, "Genre removal failed", null));
        }
        return ResponseEntity.ok().body(new BaseResponseModel(true, null, "Genre removed successfully"));
    }

    @PutMapping("/update/{genreId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class)))
    })
    public ResponseEntity<BaseResponseModel> removeGenreById(@PathVariable long genreId, @RequestBody UpdateBookGenreRequestModel payload, @RequestHeader String Authorization) {
        Boolean success = bookGenreManagerService.updateItem(payload, genreId);
        if (!success) {
            return ResponseEntity.ok().body(new BaseResponseModel(false, "Genre update failed", null));
        }
        return ResponseEntity.ok().body(new BaseResponseModel(true, null, "Genre updated successfully"));
    }

}
