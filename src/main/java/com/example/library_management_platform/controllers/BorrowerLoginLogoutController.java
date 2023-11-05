package com.example.library_management_platform.controllers;


import com.example.library_management_platform.models.api.request.LoginRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.models.api.response.LoginResponseModelModel;
import com.example.library_management_platform.services.BorrowerLoginLogoutService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BorrowerLoginLogoutController {

    @Autowired
    BorrowerLoginLogoutService borrowerLoginLogoutService;

    @PostMapping("/login")
    public BaseResponseModel login(@RequestBody @Valid LoginRequestModel loginRequestModel, BindingResult result){
        try{
            if(result.hasErrors()){
                List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
                return new LoginResponseModelModel(false, String.join(", ", errors), "",null);
            }
            String token = borrowerLoginLogoutService.login(loginRequestModel);
            if(token == null){
                return new LoginResponseModelModel(false, "Oops!! token generation failed.", null,null);
            }
            return new LoginResponseModelModel(false, null, "Woo hoo!! your token generated successfully.", new LoginResponseModelModel.DataObj(token));

        }catch ( Exception e){
            log.error("BorrowerLoginLogoutController, login exception raised!! payload: {}",loginRequestModel,e);
            return new LoginResponseModelModel(false, "Oops!! something went wrong.", null,null);
        }
    }
}
