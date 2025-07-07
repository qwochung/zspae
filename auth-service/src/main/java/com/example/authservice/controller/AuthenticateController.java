package com.example.authservice.controller;

import com.example.authservice.dto.request.LoginRequest;
import com.example.authservice.dto.response.ApiResponse;
import com.example.authservice.dto.response.AuthenticateResponse;
import com.example.authservice.service.AuthenticateService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(value = "auth")
public class AuthenticateController {
    AuthenticateService authenticateService;


    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ApiResponse<AuthenticateResponse> login (@RequestBody LoginRequest loginRequest) {
        return ApiResponse.<AuthenticateResponse>builder()
                .result(authenticateService.login(loginRequest))
                .build();
    }


}
