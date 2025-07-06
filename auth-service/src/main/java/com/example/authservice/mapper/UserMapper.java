package com.example.authservice.mapper;

import com.example.authservice.dto.request.CreateUserRequest;
import com.example.authservice.dto.response.UserResponse;
import com.example.authservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {
    public User toUser(CreateUserRequest createUserRequest);
    public UserResponse toUserResponse(User user);

}
