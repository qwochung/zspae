package com.example.authservice.service.impl;

import com.example.authservice.constant.ErrorCode;
import com.example.authservice.dto.request.LoginRequest;
import com.example.authservice.dto.response.AuthenticateResponse;
import com.example.authservice.dto.response.UserResponse;
import com.example.authservice.entity.User;
import com.example.authservice.exception.AppException;
import com.example.authservice.mapper.UserMapper;
import com.example.authservice.service.AuthenticateService;
import com.example.authservice.service.UserService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticateServiceImpl  implements AuthenticateService {

    UserService userService;
    PasswordEncoder passwordEncoder;
    UserMapper userMapper;

    @NonFinal
    @Value("${jwt.signerKey}")
    String jwtSecret ;

    @Override
    public AuthenticateResponse login(LoginRequest loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new AppException(ErrorCode.NOT_FOUND));
        boolean passwordMatch = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (!passwordMatch) {
            throw new AppException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        var token = generateToken(user);

        return AuthenticateResponse.builder()
                .token(token.token)
                .expires(token.expires)
                .build();
    }






    private TokenInfo generateToken(User user)  {
        try{
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

            Date issuedAt = new Date();
            Date expiresAt = new Date(Instant.ofEpochMilli(issuedAt.getTime())
                    .plus(1, ChronoUnit.HOURS).toEpochMilli());

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getName())
                    .issuer("Auth-service")
                    .issueTime(issuedAt)
                    .expirationTime(expiresAt)
                    .jwtID(UUID.randomUUID().toString())
//                .claim("scope", buildScope(user))
                    .claim("userId", user.getId())
                    .build();

            Payload payload = new Payload(claimsSet.toJSONObject());

            JWSObject jwsObject = new JWSObject(header, payload);

            jwsObject.sign(new MACSigner(jwtSecret.getBytes()));
            return new TokenInfo(jwsObject.serialize(), expiresAt);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new AppException(ErrorCode.AUTHENTICATION_FAILED);
        }

    }




    record TokenInfo(String token, Date expires) {}
}

