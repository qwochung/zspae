package com.example.authservice.mapper;

import com.example.authservice.dto.request.RoleRequest;
import com.example.authservice.dto.response.RoleResponse;
import com.example.authservice.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public  interface RoleMapper {
    public Role toRole(RoleRequest roleRequest);
    public Role toRole(RoleResponse roleResponse);
    public RoleResponse toRoleResponse(Role role);
}
