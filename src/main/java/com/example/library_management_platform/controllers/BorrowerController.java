package com.example.library_management_platform.controllers;


import com.example.library_management_platform.models.api.request.AddBorrowerRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.models.api.response.BorrowerDetailsResponseModel;
import com.example.library_management_platform.services.BorrowerManagerService;
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
    public BaseResponseModel addBorrower(@RequestBody @Valid AddBorrowerRequestModel payload, BindingResult result){
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
    public BaseResponseModel getBorrower(@PathVariable long borrowerId){
        try{
            BorrowerDetailsResponseModel.DataObj borrowerDetailsResponseDataObj = borrowerManagerService.getUserById(borrowerId);
            return new BorrowerDetailsResponseModel(true,null,"Woo hoo!! your borrower details fetched successfully,",borrowerDetailsResponseDataObj);
        }catch (Exception e){
            log.error("BorrowerController, getBorrower exception raised!! borrowerId:{}",borrowerId,e);
            return new BorrowerDetailsResponseModel(false,"Oops!! something went wrong.",null,null);}
    }
}
