package com.example.library_management_platform.controllers;

import com.example.library_management_platform.models.api.request.AddBookGenreRequestModel;
import com.example.library_management_platform.models.api.request.UpdateBookGenreRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.models.api.response.GetAllBookGenresResponseModel;
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

import java.awt.print.Pageable;
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
            return ResponseEntity.internalServerError().body(new BaseResponseModel(false, "something went wrong", null));
        }
    }

    @GetMapping("/all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetAllBookGenresResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetAllBookGenresResponseModel.class)))
    })
    public ResponseEntity<GetAllBookGenresResponseModel> getAllGenres(Pageable pageable) {
        try {
            List<GetAllBookGenresResponseModel.AllGenreObj> genresObjList = bookGenreManagerService.getAllItems(pageable);
            return ResponseEntity.ok().body(new GetAllBookGenresResponseModel(true, null, "Woo hoo!! your genres fetched successfully.", new GetAllBookGenresResponseModel.AllGenreDetailsData(genresObjList)));
        } catch (Exception e) {
            log.error("GenreController, getAllGenres exception raised!!", e);
            return ResponseEntity.internalServerError().body(new GetAllBookGenresResponseModel(false, "Oops!! something went wrong", null, null));
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
            return ResponseEntity.ok().body(new GetBookGenreByIdResponseModel(true, null, "Woo hoo!! your genre details fetched successfully.", new GetBookGenreByIdResponseModel.BookGenreByIdDetailsData(bookGenreByIdDetails)));
        } catch (Exception e) {
            log.error("GenreController, getGenreByID exception raised!!", e);
            return ResponseEntity.internalServerError().body(new GetBookGenreByIdResponseModel(false, "Oops!! something went wrong", null, null));
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
            return ResponseEntity.ok().body(new BaseResponseModel(success, "Woo hoo!! your genre removed successfully", null));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new BaseResponseModel(false, "Oops!! something went wrong", null));
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
            return ResponseEntity.ok().body(new BaseResponseModel(success, "Woo hoo!! your genre updated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new BaseResponseModel(false, "Oops!! something went wrong", null));
        }
    }

}
