package com.example.profileservice.service.impl;

import com.example.profileservice.contants.ErrorCode;
import com.example.profileservice.dto.request.UserRequest;
import com.example.profileservice.dto.response.UserResponse;
import com.example.profileservice.entity.User;
import com.example.profileservice.exception.AppException;
import com.example.profileservice.mapper.UserMapper;
import com.example.profileservice.repository.UserRepository;
import com.example.profileservice.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserResponse crate(UserRequest userrequest) {
        User user = userMapper.toUser(userrequest);
        if (user.getId() == null) {
            throw new AppException(ErrorCode.BAD_REQUEST);
        }
        user.setCreatedAt(System.currentTimeMillis());
        user =  userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public List<User> findAllByName(String name) {
        return List.of();
    }

    @Override
    public User update(UserRequest userrequest) {
        return null;
    }
}
