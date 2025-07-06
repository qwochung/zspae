package com.example.authservice.service.impl;

import com.example.authservice.constant.ERole;
import com.example.authservice.dto.request.RoleRequest;
import com.example.authservice.dto.response.ApiResponse;
import com.example.authservice.dto.response.RoleResponse;
import com.example.authservice.entity.Role;
import com.example.authservice.mapper.RoleMapper;
import com.example.authservice.repository.RoleRepository;
import com.example.authservice.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
    RoleMapper roleMapper;
    RoleRepository roleRepository;


    @Override
    public RoleResponse addRole(RoleRequest roleRequest) {
        Role role = roleMapper.toRole(roleRequest);
        role = roleRepository.save(role);
        RoleResponse response = roleMapper.toRoleResponse(role);
        return response;
    }

    @Override
    public  RoleResponse findById(String nameId) {
        Optional<Role> role = roleRepository.findById(nameId);
        return role.map(roleMapper::toRoleResponse).orElse(null);
    }

    @Override
    public List<RoleResponse> findRoleResponseByUserId(Long id) {
        List<Role> roles = roleRepository.findByUserId(id);
        List<RoleResponse> roleResponses = roles.stream().map(roleMapper::toRoleResponse).toList();
        return roleResponses;
    }


}
