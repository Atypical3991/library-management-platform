package com.example.library_management_platform.controllers;


import com.example.library_management_platform.models.api.request.AddBorrowerRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.models.api.response.GetAllIssuanceResponseModel;
import com.example.library_management_platform.models.api.response.GetBorrowerDetailsResponseModel;
import com.example.library_management_platform.repositories.BorrowerRepository;
import com.example.library_management_platform.services.BorrowerManagerService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrowers")
@Slf4j
public class BorrowerController {

    @Autowired
    BorrowerManagerService borrowerManagerService;

    @Autowired
    BorrowerRepository borrowerRepository;

    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetAllIssuanceResponseModel.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetAllIssuanceResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetAllIssuanceResponseModel.class)))
    })
    public ResponseEntity<BaseResponseModel> addBorrower(@RequestBody @Valid AddBorrowerRequestModel payload, @RequestHeader String Authorization) {
        try {
            borrowerManagerService.createUser(payload);
            return ResponseEntity.ok().body(new BaseResponseModel(true, "", "Borrower added successfully."));
        } catch (Exception e) {
            log.error("BorrowerController, addBorrower exception raised!! payload : {}", payload, e);
            return ResponseEntity.internalServerError().body(new BaseResponseModel(false, "Something went wrong.", null));
        }
    }

    //TODO: add support for deactivation and reactivation of borrowers by Id.

    @GetMapping("/{borrowerId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetBorrowerDetailsResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetBorrowerDetailsResponseModel.class)))
    })
    public ResponseEntity<GetBorrowerDetailsResponseModel> getBorrower(@PathVariable long borrowerId, @RequestHeader String Authorization) {
        try {
            GetBorrowerDetailsResponseModel.BorrowerDetails borrowerDetailsResponseBorrowerDetails = borrowerManagerService.getUserById(borrowerId, Authorization);
            return ResponseEntity.ok().body(new GetBorrowerDetailsResponseModel(true, null, "Borrower details fetched successfully,", borrowerDetailsResponseBorrowerDetails));
        } catch (Exception e) {
            log.error("BorrowerController, getBorrower exception raised!! borrowerId:{}", borrowerId, e);
            return ResponseEntity.internalServerError().body(new GetBorrowerDetailsResponseModel(false, "Something went wrong.", null, null));
        }
    }

}
