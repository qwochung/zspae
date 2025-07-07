package com.example.authservice.controller;

import com.example.authservice.dto.request.CreateUserRequest;
import com.example.authservice.dto.response.ApiResponse;
import com.example.authservice.dto.response.UserResponse;
import com.example.authservice.entity.User;
import com.example.authservice.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;


    @RequestMapping(method = RequestMethod.POST, value = "/registration")
    public ApiResponse<UserResponse> addUser(@RequestBody CreateUserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.create(request))
                .build();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable Long id) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.findUserResponseById(id))
                .build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ApiResponse<List<UserResponse>> getAllUser() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.findAllUserResponses())
                .build();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/email")
    public ApiResponse<Boolean> checkEmailExists(@Param("email") String email) {
        return ApiResponse.<Boolean>builder()
                .result(userService.existsByEmail(email))
                .build();
    }





}

