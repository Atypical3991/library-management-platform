package com.example.library_management_platform.controllers;

import com.example.library_management_platform.convertors.CreateBookIssuanceModelToBookIssuanceConvertor;
import com.example.library_management_platform.models.api.request.CreateBookIssuanceRequestModel;
import com.example.library_management_platform.models.api.request.UpdateIssuanceRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.models.api.response.GetAllIssuanceResponseModel;
import com.example.library_management_platform.models.entities.BookIssuance;
import com.example.library_management_platform.repositories.BookIssuanceRepository;
import com.example.library_management_platform.repositories.BookRepository;
import com.example.library_management_platform.repositories.LibraryMembershipRepository;
import com.example.library_management_platform.services.BookIssuanceManagerService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/issuance")
@Slf4j
public class BookIssuanceController {


    @Autowired
    BookIssuanceManagerService bookIssuanceManagerService;

    @Autowired
    BookIssuanceRepository bookIssuanceRepository;

    @Autowired
    LibraryMembershipRepository libraryMembershipRepository;

    @Autowired
    BookRepository bookRepository;


    @Autowired
    CreateBookIssuanceModelToBookIssuanceConvertor createBookIssuanceModelToBookIssuanceConvertor;

    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "200", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class)))
    })
    public ResponseEntity<BaseResponseModel> issueBook(@RequestBody @Valid CreateBookIssuanceRequestModel payload, @RequestHeader String Authorization) {
        try {
            Boolean success = bookIssuanceManagerService.createIssuance(payload);
            if (!success) {
                return ResponseEntity.internalServerError().body(new BaseResponseModel(false, "Something went wrong!!", null));
            }
            return ResponseEntity.ok().body(new BaseResponseModel(true, null, "Book issued successfully"));

        } catch (Exception e) {
            log.error("BookIssuanceController, issueBook exception raised!! payload: {}", payload, e);
            return ResponseEntity.internalServerError().body(new BaseResponseModel(false, "Something went wrong!!", null));
        }
    }

    @PutMapping("/update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "200", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class)))
    })
    public ResponseEntity<BaseResponseModel> updateIssuance(@RequestBody @Valid UpdateIssuanceRequestModel payload, @RequestHeader String Authorization) {
        Boolean success = bookIssuanceManagerService.updateIssuance(payload);
        if (success) {
            return ResponseEntity.ok().body(new BaseResponseModel(true, "Something went wrong", null));
        }
        return ResponseEntity.ok().body(new BaseResponseModel(true, null, "Successfully updated."));

    }

    @GetMapping("/all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetAllIssuanceResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetAllIssuanceResponseModel.class)))
    })
    public ResponseEntity<GetAllIssuanceResponseModel> getAllActiveIssuance(@RequestParam BookIssuance.StatusEnum statusEnum, @RequestHeader String Authorization) {
        List<GetAllIssuanceResponseModel.AllIssuanceObj> issuanceObjList = bookIssuanceManagerService.getAllIssuance(statusEnum);
        return ResponseEntity.ok().body(new GetAllIssuanceResponseModel(true, null, null, new GetAllIssuanceResponseModel.AllIssuanceDetailsData(issuanceObjList)));
    }
}
