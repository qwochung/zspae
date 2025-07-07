package com.example.authservice.service.impl;

import com.example.authservice.constant.ERole;
import com.example.authservice.constant.ErrorCode;
import com.example.authservice.constant.Status;
import com.example.authservice.dto.request.CreateUserRequest;
import com.example.authservice.dto.response.RoleResponse;
import com.example.authservice.dto.response.UserResponse;
import com.example.authservice.entity.Role;
import com.example.authservice.entity.User;
import com.example.authservice.exception.AppException;
import com.example.authservice.mapper.RoleMapper;
import com.example.authservice.mapper.UserMapper;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.service.RoleService;
import com.example.authservice.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    RoleMapper roleMapper;
    RoleService roleService;
    PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserResponse create(CreateUserRequest request) {
        if (existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        User user = userMapper.toUser(request);
        Role role = roleMapper.toRole(roleService.findById(String.valueOf(ERole.USER)));
        user.setRoles(Set.of(role));
        user.setCreatedAt(System.currentTimeMillis());
        user.setStatus(Status.PENDING);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public User update(User user, Long id) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserResponse findUserResponseByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        UserResponse userResponse = userMapper.toUserResponse(user);
        return userResponse;
    }

    @Override
    public UserResponse findUserResponseById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        UserResponse userResponse = userMapper.toUserResponse(user);
        return userResponse;
    }


    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        user.setStatus(Status.DELETED);
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserResponse> findAllUserResponses() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = users.stream().map(userMapper::toUserResponse).toList();
        return userResponses;
    }
}
