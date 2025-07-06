package com.example.authservice.controller;

import com.example.authservice.constant.ERole;
import com.example.authservice.dto.request.RoleRequest;
import com.example.authservice.dto.response.ApiResponse;
import com.example.authservice.dto.response.RoleResponse;
import com.example.authservice.entity.Role;
import com.example.authservice.mapper.RoleMapper;
import com.example.authservice.service.RoleService;
import com.example.authservice.service.impl.RoleServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(value = "/role")
public class RoleController {
    RoleService roleService;


    @RequestMapping(method = RequestMethod.POST, value = "")
    public ApiResponse<RoleResponse> addRole(@RequestBody RoleRequest roleRequest) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.addRole(roleRequest))
                .build();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{nameId}")
    public ApiResponse<RoleResponse> getRole(@PathVariable String nameId) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.findById(nameId))
                .build();
    }


}
