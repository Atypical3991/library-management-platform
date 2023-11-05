package com.example.library_management_platform.controllers;


import com.example.library_management_platform.models.api.request.BorrowerLoginRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponse;
import com.example.library_management_platform.models.api.response.BorrowerLoginResponseModel;
import com.example.library_management_platform.services.BorrowerLoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/login")
public class BorrowerLoginController {

    @Autowired
    BorrowerLoginService borrowerLoginService;

    @PostMapping("")
    public BaseResponse login(@RequestBody @Valid BorrowerLoginRequestModel borrowerLoginRequestModel, BindingResult result){
        if(result.hasErrors()){
            List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new BorrowerLoginResponseModel(false, String.join(", ", errors), "",null);
        }
        String token = borrowerLoginService.login(borrowerLoginRequestModel);
        if(token == null){
            return new BorrowerLoginResponseModel(false, "something went wrong.", null,null);
        }
        return new BorrowerLoginResponseModel(false, null, "Token generated successfully.", new BorrowerLoginResponseModel.DataObj(token));
    }
}
