package com.example.library_management_platform.controllers;

import com.example.library_management_platform.models.api.request.LoginRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.models.api.response.LoginResponseModelModel;
import com.example.library_management_platform.repositories.SessionsRepository;
import com.example.library_management_platform.services.LibraryManagerLoginLogoutService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/library/managers")
@Slf4j
public class LibraryManagerLoginLogoutController {

    @Autowired
    LibraryManagerLoginLogoutService libraryManagerLoginLogoutService;

    @Autowired
    SessionsRepository sessionsRepository;

    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseModelModel.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseModelModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseModelModel.class)))
    })
    public ResponseEntity<LoginResponseModelModel> login(@RequestBody @Valid LoginRequestModel loginRequestModel) {
        try {
            String token = libraryManagerLoginLogoutService.login(loginRequestModel);
            if (token == null) {
                return ResponseEntity.internalServerError().body(new LoginResponseModelModel(false, "Token generation failed.", "", null));
            }
            return ResponseEntity.ok().body(new LoginResponseModelModel(true, null, "Logged-in successfully.", new LoginResponseModelModel.LoginResponseDetailsData(token)));
        } catch (Exception e) {
            log.error("LibraryManagerLoginLogoutController, login exception raised!! payload: {}", loginRequestModel, e);
            return ResponseEntity.internalServerError().body(new LoginResponseModelModel(false, "Something went wrong.", "", null));
        }
    }

    @PostMapping("/logout")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseModelModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseModelModel.class)))
    })
    public ResponseEntity<BaseResponseModel> logout(@RequestHeader String Authorization) {
        //TODO:  add userId validation
        try {
            sessionsRepository.deleteSessionsByToken(Authorization);
            return ResponseEntity.ok().body(new BaseResponseModel(true, null, "Logged-out successfully"));
        } catch (Exception e) {
            log.error("LibraryManagerLoginLogoutController, logout exception raised!!", e);
            return ResponseEntity.internalServerError().body(new BaseResponseModel(false, "Something went wrong.", null));
        }
    }
}
