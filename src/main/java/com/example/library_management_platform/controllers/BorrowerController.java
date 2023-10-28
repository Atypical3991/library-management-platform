package com.example.library_management_platform.controllers;


import com.example.library_management_platform.models.api.request.AddBorrowerRequest;
import com.example.library_management_platform.models.api.response.BaseResponse;
import com.example.library_management_platform.models.api.response.BorrowerDetailsResponse;
import com.example.library_management_platform.services.BorrowerManagerService;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrowers")
@Slf4j
public class BorrowerController {

    @Autowired
    BorrowerManagerService borrowerManagerService;

    @PostMapping("")
    public BaseResponse addBorrower(@RequestBody AddBorrowerRequest payload){
        try{
            borrowerManagerService.createUser(payload);
            return new BaseResponse(true,"","Congrats!! borrower added successfully.");
        }catch (Exception e){
            log.error("BorrowerController, addUser exception raised!! payload : {}",payload,e);
            return new BaseResponse(true,"Oops!! something went wrong.",null);

        }
    };

    @GetMapping("/{borrowerId}")
    public BaseResponse getBorrower(@PathVariable long borrowerId){
        try{
            BorrowerDetailsResponse.DataObj borrowerDetailsResponseDataObj = borrowerManagerService.getUserById(borrowerId);
            return new BorrowerDetailsResponse(true,null,"Borrower details fetched successfully,",borrowerDetailsResponseDataObj);
        }catch (Exception e){
            log.error("BorrowerController, getBorrower exception raised!! borrowerId:{}",borrowerId,e);
            return new BorrowerDetailsResponse(true,"Oops!! something went wrong.",null,null);}
    }
}
