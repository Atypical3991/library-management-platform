package com.example.library_management_platform.controllers;


import com.example.library_management_platform.models.api.request.AddUserRequest;
import com.example.library_management_platform.models.api.response.BaseResponse;
import com.example.library_management_platform.models.api.response.GetUserProfileResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    /**
     * addUser :- to create a new User.
     * @param body (contains user table related details)
     * @return BaseResponse (base response)
     */
    @PostMapping("/register")
    public BaseResponse addUser(@RequestBody AddUserRequest body){};

    /**
     * removeUser :- to remove a user.
     * @param userId (id from user table)
     * @return BaseResponse (base response)
     */
    @DeleteMapping("/{userId}/remove")
    public BaseResponse removeUser(@PathVariable Long userId){};

    /**
     * getUserProfile :- to get user profile details.
     * @param userId (id of user table)
     * @return GetUserProfileResponse (contains user profile information)
     */

    @GetMapping("/{userId}/profile")
    public GetUserProfileResponse getUserProfile(@PathVariable Long userId){};

    /**
     * modifyUserProfile :- to modify user profile
     * @param userId (id from user table)
     * @return BaseResponse (base response)
     */
    @PutMapping("/{userId}/modify")
    public BaseResponse modifyUserProfile(@PathVariable Long userId){};

}
