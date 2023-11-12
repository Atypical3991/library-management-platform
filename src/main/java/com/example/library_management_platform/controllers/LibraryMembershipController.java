package com.example.library_management_platform.controllers;


import com.example.library_management_platform.models.api.request.CreateMembershipRequestModel;
import com.example.library_management_platform.models.api.request.RenewMembershipRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.models.entities.Borrower;
import com.example.library_management_platform.models.entities.LibraryMembership;
import com.example.library_management_platform.repositories.BorrowerRepository;
import com.example.library_management_platform.repositories.LibraryMembershipRepository;
import com.example.library_management_platform.utils.DateUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/membership")
@Slf4j
public class LibraryMembershipController {

    @Autowired
    BorrowerRepository borrowerRepository;

    @Autowired
    LibraryMembershipRepository libraryMembershipRepository;

    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class)))
    })
    public BaseResponseModel createMembership(@RequestBody @Valid CreateMembershipRequestModel payload, @RequestHeader String Authorization, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new BaseResponseModel(false, String.join(", ", errors), "");
        }
        try {
            Optional<Borrower> borrowerOpt = borrowerRepository.findById(payload.getBorrowerId());
            if (borrowerOpt.isEmpty()) {
                return new BaseResponseModel(false, "Borrower not found", null);
            }
            if (borrowerOpt.get().getLibraryMembership() != null) {
                return new BaseResponseModel(false, "Membership already created", null);
            }
            LibraryMembership libraryMembership = new LibraryMembership();
            libraryMembership.setBorrower(borrowerOpt.get());
            libraryMembership.setStatus(LibraryMembership.StatusEnum.ACTIVE);
            libraryMembership.setIssueDate((Date) DateUtil.convertToDate(payload.getIssuedAt()));
            libraryMembership.setExpiryDate((Date) DateUtil.convertToDate(payload.getExpiredAt()));
            libraryMembershipRepository.save(libraryMembership);
            borrowerOpt.get().setLibraryMembership(libraryMembership);
            borrowerRepository.save(borrowerOpt.get());
            return new BaseResponseModel(true, null, "Whoohoo!! membership created successfully.");
        } catch (Exception e) {
            log.error("LibraryMembershipController, createMembership exception raised!! payload: {}", payload, e);
            return new BaseResponseModel(false, "Something went wrong.", null);
        }
    }

    @PostMapping("/renew")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class)))
    })
    public BaseResponseModel renewMembership(@RequestBody @Valid RenewMembershipRequestModel payload, @RequestHeader String Authorization, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new BaseResponseModel(false, String.join(", ", errors), "");
        }
        try {
            Optional<LibraryMembership> membershipOpt = libraryMembershipRepository.findById(payload.getMembershipId());
            if (membershipOpt.isEmpty()) {
                return new BaseResponseModel(false, "Membership not found!!", null);
            }
            membershipOpt.get().setExpiryDate((Date) DateUtil.convertToDate(payload.getExpiredAt()));
            membershipOpt.get().setIssueDate((Date) DateUtil.convertToDate(payload.getIssuedAt()));
            membershipOpt.get().setStatus(LibraryMembership.StatusEnum.ACTIVE);
            libraryMembershipRepository.save(membershipOpt.get());
            return new BaseResponseModel(true, null, "Whoohoo!! membership updated successfully.");
        } catch (Exception e) {
            log.error("LibraryMembershipController, renewMembership exception raised!! payload: {}", payload, e);
            return new BaseResponseModel(false, "Something went wrong.", null);
        }

    }

    @PutMapping("/cancel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponseModel.class)))
    })
    public BaseResponseModel cancelMembership(@RequestParam long membershipId, @RequestHeader String Authorization) {

        try {
            Optional<LibraryMembership> membershipOpt = libraryMembershipRepository.findById(membershipId);
            if (membershipOpt.isEmpty()) {
                return new BaseResponseModel(false, "Membership not found!!", null);
            }
            membershipOpt.get().setStatus(LibraryMembership.StatusEnum.IN_ACTIVE);
            libraryMembershipRepository.save(membershipOpt.get());
            return new BaseResponseModel(true, null, "Whoohoo!! membership updated successfully.");
        } catch (Exception e) {
            log.error("LibraryMembershipController, renewMembership exception raised!! membershipId: {}", membershipId, e);
            return new BaseResponseModel(false, "Something went wrong.", null);
        }

    }
}
