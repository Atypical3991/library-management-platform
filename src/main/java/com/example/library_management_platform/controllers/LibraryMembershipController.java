package com.example.library_management_platform.controllers;


import com.example.library_management_platform.models.api.request.CreateMembershipRequestModel;
import com.example.library_management_platform.models.api.request.RenewMembershipRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.repositories.BorrowerRepository;
import com.example.library_management_platform.repositories.LibraryMembershipRepository;
import com.example.library_management_platform.services.LibraryMembershipService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/library/membership")
@Slf4j
public class LibraryMembershipController {

    @Autowired
    BorrowerRepository borrowerRepository;

    @Autowired
    LibraryMembershipRepository libraryMembershipRepository;

    @Autowired
    LibraryMembershipService libraryMembershipService;

    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class)))
    })
    public ResponseEntity<BaseResponseModel> createMembership(@RequestBody @Valid CreateMembershipRequestModel payload, @RequestHeader String Authorization) {
        Boolean success = libraryMembershipService.createMembership(payload);
        if (!success) {
            return ResponseEntity.internalServerError().body(new BaseResponseModel(false, "Something went wrong.", null));
        }
        return ResponseEntity.ok().body(new BaseResponseModel(true, null, "Membership created successfully."));
    }

    @PostMapping("/renew")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class)))
    })
    public ResponseEntity<BaseResponseModel> renewMembership(@RequestBody @Valid RenewMembershipRequestModel payload, @RequestHeader String Authorization, BindingResult result) {
        Boolean success = libraryMembershipService.renewMembership(payload);
        if (!success) {
            return ResponseEntity.internalServerError().body(new BaseResponseModel(false, "Something went wrong.", null));
        }
        return ResponseEntity.ok().body(new BaseResponseModel(true, null, "Membership renewed successfully."));
    }

    @PutMapping("/cancel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class)))
    })
    public ResponseEntity<BaseResponseModel> cancelMembership(@RequestParam long membershipId, @RequestHeader String Authorization) {
        Boolean success = libraryMembershipService.cancelMembership(membershipId);
        if (!success) {
            return ResponseEntity.ok().body(new BaseResponseModel(true, "Membership cancellation failed.", null));
        }
        return ResponseEntity.ok().body(new BaseResponseModel(true, null, "Membership cancelled successfully."));
    }
}
