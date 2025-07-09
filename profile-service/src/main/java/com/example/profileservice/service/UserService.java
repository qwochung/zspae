package com.example.profileservice.service;

import com.example.profileservice.dto.request.UserRequest;
import com.example.profileservice.dto.response.UserResponse;
import com.example.profileservice.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    UserResponse crate (UserRequest userrequest);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    List<User> findAll();
    List<User> findAllByName(String name);
    User update(UserRequest userrequest);


}
