package com.example.library_management_platform.controllers;


import com.example.library_management_platform.models.api.request.AddUserRequest;
import com.example.library_management_platform.models.api.response.BaseResponse;
import com.example.library_management_platform.models.api.response.GetAllIssuance;
import com.example.library_management_platform.models.api.response.GetUserProfileResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class BorrowerController {

//    @PostMapping("/register")
//    public BaseResponse addUser(@RequestBody AddUserRequest body){
//        return new BaseResponse();
//    };


//    @DeleteMapping("/{userId}/remove")
//    public BaseResponse removeUser(@PathVariable Long userId){
//        return new BaseResponse();
//    };


//    @GetMapping("/{userId}/profile")
//    public GetUserProfileResponse getUserProfile(@PathVariable Long userId){
//        return new GetUserProfileResponse();
//    };


//    @PutMapping("/{userId}/modify")
//    public BaseResponse modifyUserProfile(@PathVariable Long userId){
//        return new BaseResponse();
//    };

    @GetMapping("{userId}/all_issuance")
    public GetAllIssuance getMyAllIssuance(@PathVariable Long userId){
        return new GetAllIssuance();
    }
}
