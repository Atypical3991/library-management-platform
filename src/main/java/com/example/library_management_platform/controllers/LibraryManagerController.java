package com.example.library_management_platform.controllers;

import com.example.library_management_platform.models.api.request.AddLibraryManagerRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.models.api.response.GetLibraryManagerByIdResponseModel;
import com.example.library_management_platform.services.LibraryManagerService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/library/managers")
@Slf4j
public class LibraryManagerController {

    @Autowired
    LibraryManagerService libraryManagerService;

    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class)))
    })
    public ResponseEntity<BaseResponseModel> createLibraryManager(@Valid @RequestBody AddLibraryManagerRequestModel payload, @RequestHeader(name = "x-admin-secret-key") String secretHeader) {
        Boolean success = libraryManagerService.createUser(payload);
        if (!success) {
            return ResponseEntity.internalServerError().body(new BaseResponseModel(false, "Something went wrong.", null));
        }
        return ResponseEntity.ok().body(new BaseResponseModel(true, "", "Library Manager added successfully."));
    }


    @GetMapping("/{libraryManagerId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetLibraryManagerByIdResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetLibraryManagerByIdResponseModel.class)))
    })
    public ResponseEntity<GetLibraryManagerByIdResponseModel> getLibraryManager(@PathVariable long libraryManagerId, @RequestHeader String Authorization) {
        GetLibraryManagerByIdResponseModel.LibraryManagerByIdDetailsData libraryManagerByIdDetailsData = libraryManagerService.getUserById(libraryManagerId, Authorization);
        return ResponseEntity.ok().body(new GetLibraryManagerByIdResponseModel(true, null, "Library Manager's details fetched successfully,", libraryManagerByIdDetailsData));

    }
}
