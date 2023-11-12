package com.example.library_management_platform.controllers;


import com.example.library_management_platform.models.api.request.LoginRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.models.api.response.LoginResponseModelModel;
import com.example.library_management_platform.repositories.SessionsRepository;
import com.example.library_management_platform.services.BorrowerLoginLogoutService;
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
public class BorrowerLoginLogoutController {

    @Autowired
    BorrowerLoginLogoutService borrowerLoginLogoutService;

    @Autowired
    SessionsRepository sessionsRepository;

    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseModelModel.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseModelModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseModelModel.class)))
    })
    public LoginResponseModelModel login(@RequestBody @Valid LoginRequestModel loginRequestModel, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
                return new LoginResponseModelModel(false, String.join(", ", errors), "", null);
            }
            String token = borrowerLoginLogoutService.login(loginRequestModel);
            if (token == null) {
                return new LoginResponseModelModel(false, "Oops!! token generation failed.", null, null);
            }

            return new LoginResponseModelModel(false, null, "Woo hoo!! your token generated successfully.", new LoginResponseModelModel.LoginResponseDetailsData(token));

        } catch (Exception e) {
            log.error("BorrowerLoginLogoutController, login exception raised!! payload: {}", loginRequestModel, e);
            return new LoginResponseModelModel(false, "Oops!! something went wrong.", null, null);
        }
    }

    @PostMapping("/logout")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseModelModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseModelModel.class)))
    })
    public BaseResponseModel logout(@RequestHeader String Authorization) {
        try {
            sessionsRepository.deleteSessionsByToken(Authorization);
            return new BaseResponseModel(true, null, "You logged out.");
        } catch (Exception e) {
            log.error("LibraryManagerLoginLogoutController, login exception raised!!", e);
            return new BaseResponseModel(false, "Oops!! something went wrong.", null);
        }
    }


}
