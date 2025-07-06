package com.example.authservice.dto.response;

import com.example.authservice.entity.BaseEntity;
import com.example.authservice.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse extends BaseEntity {
    String name;
    String email;
    Set<RoleResponse> roles;
}
