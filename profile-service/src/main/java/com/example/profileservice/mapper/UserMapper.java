package com.example.profileservice.mapper;

import com.example.profileservice.dto.request.UserRequest;
import com.example.profileservice.dto.response.UserResponse;
import com.example.profileservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public User toUser(UserRequest request);
    UserRequest toUserRequest(User user);
    UserResponse toUserResponse(User user);
}
