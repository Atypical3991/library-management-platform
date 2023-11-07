package com.example.library_management_platform.controllers;

import com.example.library_management_platform.models.api.request.AddLibraryManagerRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.models.api.response.BorrowerDetailsResponseModel;
import com.example.library_management_platform.models.api.response.GetLibraryManagerByIdResponseModel;
import com.example.library_management_platform.services.LibraryManagerService;
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
    public BaseResponseModel createLibraryManager(@RequestBody @Valid  AddLibraryManagerRequestModel payload, BindingResult result){
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
    public BaseResponseModel getBorrower(@PathVariable long libraryManagerId){
        try{
            GetLibraryManagerByIdResponseModel.DataObj dataObj = libraryManagerService.getUserById(libraryManagerId);
            return new GetLibraryManagerByIdResponseModel(true,null,"Woo hoo!! your borrower details fetched successfully,",dataObj);
        }catch (Exception e){
            log.error("BorrowerController, getBorrower exception raised!! borrowerId:{}",libraryManagerId,e);
            return new GetLibraryManagerByIdResponseModel(false,"Oops!! something went wrong.",null,null);}
    }
}
