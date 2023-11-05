package com.example.library_management_platform.controllers;


import com.example.library_management_platform.models.api.request.LoginRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.models.api.response.LoginResponseModelModel;
import com.example.library_management_platform.services.BorrowerLoginLogoutService;
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
@RequestMapping("/api/borrowers")
public class BorrowerLoginLogoutController {

    @Autowired
    BorrowerLoginLogoutService borrowerLoginLogoutService;

    @PostMapping("/login")
    public BaseResponseModel login(@RequestBody @Valid LoginRequestModel loginRequestModel, BindingResult result){
        if(result.hasErrors()){
            List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new LoginResponseModelModel(false, String.join(", ", errors), "",null);
        }
        String token = borrowerLoginLogoutService.login(loginRequestModel);
        if(token == null){
            return new LoginResponseModelModel(false, "something went wrong.", null,null);
        }
        return new LoginResponseModelModel(false, null, "Token generated successfully.", new LoginResponseModelModel.DataObj(token));
    }
}
