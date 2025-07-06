package com.example.authservice.service;

import com.example.authservice.constant.ERole;
import com.example.authservice.dto.request.RoleRequest;
import com.example.authservice.dto.response.RoleResponse;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    RoleResponse addRole(RoleRequest roleRequest);
    RoleResponse findById(String name);

    List<RoleResponse> findRoleResponseByUserId(Long id);
}
