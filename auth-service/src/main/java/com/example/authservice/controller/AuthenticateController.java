package com.example.authservice.controller;

import com.example.authservice.dto.request.LoginRequest;
import com.example.authservice.dto.request.RequestToken;
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

    @RequestMapping(method = RequestMethod.POST, value = "/verify")
    public ApiResponse<Boolean> verifyToken (@RequestBody RequestToken request) {
        return ApiResponse.<Boolean>builder()
                .result(authenticateService.verifyToken(request))
                .build();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/refresh-token")
    public ApiResponse<AuthenticateResponse> refreshToken (@RequestBody RequestToken request) {
        return ApiResponse.<AuthenticateResponse>builder()
                .result(authenticateService.refreshToken(request))
                .build();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/logout")
    public ApiResponse<Boolean> logout (@RequestBody RequestToken request) {
        authenticateService.logout(request);
        return ApiResponse.<Boolean>builder()
                .result(true)
                .build();
    }


}
