package com.example.library_management_platform.controllers;

import com.example.library_management_platform.models.api.request.LoginRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.models.api.response.LoginResponseModelModel;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/library/managers")
public class LibraryManagerLoginLogoutController {

    @PostMapping("/login")
    public BaseResponseModel login(@RequestBody  @Valid  LoginRequestModel loginRequestModel, BindingResult result){
        if(result.hasErrors()){
            List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new LoginResponseModelModel(false, String.join(", ", errors), "",null);
        }

    }

}
