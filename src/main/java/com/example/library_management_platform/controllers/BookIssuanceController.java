package com.example.library_management_platform.controllers;

import com.example.library_management_platform.models.api.request.IssueBookRequest;
import com.example.library_management_platform.models.api.response.BaseResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/book/issue")
public class BookIssuanceController {

//
//    /**
//     * issueBook:- to create an issuance
//     * @param body (contains details regarding a new issuance)
//     * @return BaseResponse (base response)
//     */
//    @PostMapping("/")
//    public BaseResponse issueBook(@RequestBody IssueBookRequest body){
//        return new BaseResponse();
//    };

//    /**
//     * removeIssuance:- to remove an active issuance.
//     * @param issueId (id of issue table)
//     * @return BaseResponse (base response)
//     */
//    @DeleteMapping("/{issueId}/remove")
//    public BaseResponse removeIssuance(@PathVariable Long issueId){
//        return new BaseResponse();
//    };

//    /**
//     * modifyIssuance:- to modify an active issuance.
//     * @param issueId (id of issue table)
//     * @return BaseResponse (base response)
//     */
//    @PutMapping("/{issueId}/modify")
//    public BaseResponse modifyIssuance(@PathVariable Long issueId){
//        return new BaseResponse();
//    };
}
