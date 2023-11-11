package com.example.library_management_platform.controllers;


import com.example.library_management_platform.models.api.request.AddBorrowerRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.models.api.response.GetBorrowerDetailsResponseModel;
import com.example.library_management_platform.models.api.response.GetAllIssuanceResponseModel;
import com.example.library_management_platform.services.BorrowerManagerService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowers")
@Slf4j
public class BorrowerController {

    @Autowired
    BorrowerManagerService borrowerManagerService;

    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetAllIssuanceResponseModel.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetAllIssuanceResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetAllIssuanceResponseModel.class)))
    })
    public BaseResponseModel addBorrower(@RequestBody @Valid AddBorrowerRequestModel payload, @RequestHeader String Authorization, BindingResult result){
        try{
            if(result.hasErrors()){
                List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
                return new BaseResponseModel(false, String.join(", ", errors), "");
            }
            borrowerManagerService.createUser(payload);
            return new BaseResponseModel(true,"","Woo hoo!! your borrower added successfully.");
        }catch (Exception e){
            log.error("BorrowerController, addUser exception raised!! payload : {}",payload,e);
            return new BaseResponseModel(false,"Oops!! something went wrong.",null);
        }
    };

    @GetMapping("/get/{borrowerId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetBorrowerDetailsResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetBorrowerDetailsResponseModel.class)))
    })
    public GetBorrowerDetailsResponseModel getBorrower(@PathVariable long borrowerId, @RequestHeader String Authorization){
        try{
            GetBorrowerDetailsResponseModel.BorrowerDetails borrowerDetailsResponseBorrowerDetails = borrowerManagerService.getUserById(borrowerId);
            return new GetBorrowerDetailsResponseModel(true,null,"Woo hoo!! your borrower details fetched successfully,", borrowerDetailsResponseBorrowerDetails);
        }catch (Exception e){
            log.error("BorrowerController, getBorrower exception raised!! borrowerId:{}",borrowerId,e);
            return new GetBorrowerDetailsResponseModel(false,"Oops!! something went wrong.",null,null);}
    }
}
