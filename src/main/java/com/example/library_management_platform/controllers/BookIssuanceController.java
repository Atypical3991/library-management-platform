package com.example.library_management_platform.controllers;

import com.example.library_management_platform.convertors.CreateBookIssuanceModelToBookIssuanceEntityModelConvertor;
import com.example.library_management_platform.models.api.request.CreateBookIssuanceModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.models.api.response.GetAllIssuanceResponseModel;
import com.example.library_management_platform.repositories.BookIssuanceRepository;
import com.example.library_management_platform.services.BookIssuanceManagerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
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
    CreateBookIssuanceModelToBookIssuanceEntityModelConvertor createBookIssuanceModelToBookIssuanceEntityModelConvertor;

    @PostMapping("")
    public BaseResponseModel issueBook(@RequestBody @Valid CreateBookIssuanceModel payload, BindingResult result){
        if(result.hasErrors()){
            List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new BaseResponseModel(false, String.join(", ", errors), "");
        }
        try{
            Boolean success = bookIssuanceManagerService.createIssuance(payload);
            if(success){
                return new BaseResponseModel(true, null, "Book issue created successfully");
            }else{
                return new BaseResponseModel(false, "something went wrong", null);

            }
        }catch (Exception e){
            log.error("BookIssuanceController, issueBook exception raised!! payload: {}",payload,e);
            return new BaseResponseModel(false, "something went wrong!!", null);
        }
    }


    @GetMapping("/all")
    public GetAllIssuanceResponseModel getAllIssuance(){
        try{
             return new GetAllIssuanceResponseModel(true, null, null, new GetAllIssuanceResponseModel.DataObj(bookIssuanceManagerService.getAllIssuance()));
        }catch (Exception e){
            log.error("BookIssuanceController, getAllIssuance exception raised!!",e);
            return new GetAllIssuanceResponseModel(false, "something went wrong!!", null,null);
        }
    }
}
