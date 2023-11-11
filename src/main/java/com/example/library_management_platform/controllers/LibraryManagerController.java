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
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public BaseResponseModel createLibraryManager(@RequestBody @Valid  AddLibraryManagerRequestModel payload, @RequestHeader(name = "x-admin-secret-key") String secretHeader,  BindingResult result){
        try{
            if(result.hasErrors()){
                List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
                return new BaseResponseModel(false, String.join(", ", errors), "");
            }
             libraryManagerService.createUser(payload);
            return new BaseResponseModel(true,"","Congrats!! Library Manager added successfully.");
        }catch (Exception e){
            log.error("BorrowerController, addUser exception raised!! payload : {}",payload,e);
            return new BaseResponseModel(false,"Oops!! something went wrong.",null);

        }
    }


    @GetMapping("/{libraryManagerId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetLibraryManagerByIdResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = GetLibraryManagerByIdResponseModel.class)))
    })
    public GetLibraryManagerByIdResponseModel getBorrower(@PathVariable long libraryManagerId){
        try{
            GetLibraryManagerByIdResponseModel.LibraryManagerByIdDetailsData libraryManagerByIdDetailsData = libraryManagerService.getUserById(libraryManagerId);
            return new GetLibraryManagerByIdResponseModel(true,null,"Woo hoo!! your borrower details fetched successfully,", libraryManagerByIdDetailsData);
        }catch (Exception e){
            log.error("BorrowerController, getBorrower exception raised!! borrowerId:{}",libraryManagerId,e);
            return new GetLibraryManagerByIdResponseModel(false,"Oops!! something went wrong.",null,null);}
    }
}
