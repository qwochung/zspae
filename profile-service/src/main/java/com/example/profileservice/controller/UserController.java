package com.example.profileservice.controller;

import com.example.profileservice.dto.request.UserRequest;
import com.example.profileservice.dto.response.ApiResponse;
import com.example.profileservice.dto.response.UserResponse;
import com.example.profileservice.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;


    @RequestMapping(method = RequestMethod.POST, value = "")
    public ApiResponse<UserResponse> save(@RequestBody UserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.crate(request))
                .build();
    }



}
