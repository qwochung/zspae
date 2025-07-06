package com.example.authservice.service;

import com.example.authservice.dto.request.CreateUserRequest;
import com.example.authservice.dto.response.UserResponse;
import com.example.authservice.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    UserResponse create(CreateUserRequest request);
    User update(User user, Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    UserResponse findUserResponseByEmail(String email);
    UserResponse findUserResponseById(Long id);
    Boolean existsByEmail(String email);
    void delete (Long id);

    List<User> findAll();
    List<UserResponse> findAllUserResponses();

}
