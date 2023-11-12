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
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public ResponseEntity<BaseResponseModel> addGenre(@RequestBody @Valid AddBookGenreRequestModel addBookGenreRequestModelPayload, @RequestHeader String Authorization, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(new BaseResponseModel(false, String.join(", ", errors), ""));
        }
        try {
            Boolean success = bookGenreManagerService.addItem(addBookGenreRequestModelPayload);
            if (success) {
                return ResponseEntity.ok().body(new BaseResponseModel(true, null, "Genre added successfully."));
            } else {
                return ResponseEntity.badRequest().body(new BaseResponseModel(true, null, "Genre addition failed."));
            }
        } catch (Exception e) {
            log.error("GenreController, addGenre exception raised!! payload : {}", addBookGenreRequestModelPayload, e);
            return ResponseEntity.internalServerError().body(new BaseResponseModel(false, "Something went wrong", null));
        }
    }

    @GetMapping("/{genreId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetBookGenreByIdResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetBookGenreByIdResponseModel.class)))
    })
    public ResponseEntity<GetBookGenreByIdResponseModel> getGenreByID(@PathVariable long genreId) {
        try {
            GetBookGenreByIdResponseModel.BookGenreByIdDetails bookGenreByIdDetails = bookGenreManagerService.getItemById(genreId);
            return ResponseEntity.ok().body(new GetBookGenreByIdResponseModel(true, null, "Genre details fetched successfully.", new GetBookGenreByIdResponseModel.BookGenreByIdDetailsData(bookGenreByIdDetails)));
        } catch (Exception e) {
            log.error("GenreController, getGenreByID exception raised!! genreId : {}", genreId, e);
            return ResponseEntity.internalServerError().body(new GetBookGenreByIdResponseModel(false, "Something went wrong", null, null));
        }
    }

    @DeleteMapping("/delete/{genreId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class)))
    })
    public ResponseEntity<BaseResponseModel> removeGenreById(@PathVariable long genreId, @RequestHeader String Authorization) {
        try {
            Boolean success = bookGenreManagerService.removeItem(genreId);
            return ResponseEntity.ok().body(new BaseResponseModel(success, "Genre removed successfully", null));
        } catch (Exception e) {
            log.error("GenreController, removeGenreById exception raised!! genreId: {}", genreId, e);
            return ResponseEntity.internalServerError().body(new BaseResponseModel(false, "Something went wrong", null));
        }
    }

    @PutMapping("/update/{genreId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class)))
    })
    public ResponseEntity<BaseResponseModel> removeGenreById(@PathVariable long genreId, @RequestBody UpdateBookGenreRequestModel payload, @RequestHeader String Authorization) {
        try {
            Boolean success = bookGenreManagerService.updateItem(payload, genreId);
            return ResponseEntity.ok().body(new BaseResponseModel(success, "Genre updated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new BaseResponseModel(false, "Something went wrong", null));
        }
    }

}
