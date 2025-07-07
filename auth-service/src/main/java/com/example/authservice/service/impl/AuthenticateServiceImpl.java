package com.example.authservice.service.impl;

import com.example.authservice.constant.ErrorCode;
import com.example.authservice.dto.request.LoginRequest;
import com.example.authservice.dto.request.RequestToken;
import com.example.authservice.dto.response.AuthenticateResponse;
import com.example.authservice.dto.response.UserResponse;
import com.example.authservice.entity.InvalidToken;
import com.example.authservice.entity.User;
import com.example.authservice.exception.AppException;
import com.example.authservice.mapper.UserMapper;
import com.example.authservice.repository.InvalidTokenRepository;
import com.example.authservice.service.AuthenticateService;
import com.example.authservice.service.InvalidTokenService;
import com.example.authservice.service.UserService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
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
import java.util.StringJoiner;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticateServiceImpl  implements AuthenticateService {

    UserService userService;
    PasswordEncoder passwordEncoder;
    InvalidTokenService invalidTokenService;


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


    @Override
    public Boolean verifyToken (RequestToken request){
        try{
            SignedJWT signedJWT = verify(request.getToken());
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public AuthenticateResponse refreshToken (RequestToken request) {
        try {
            SignedJWT signedJWT = verify(request.getToken());
            String tokenId = signedJWT.getJWTClaimsSet().getJWTID();
            Date expireTime = signedJWT.getJWTClaimsSet().getExpirationTime();

            InvalidToken invalidToken = InvalidToken.builder()
                    .id(tokenId)
                    .expiresTime(expireTime)
                    .build();

            invalidTokenService.createInvalidToken(invalidToken);

            Long userId = Long.valueOf(signedJWT.getJWTClaimsSet().getClaims().get("userId").toString());

            User user = userService.findById(userId).orElseThrow(()-> new AppException(ErrorCode.AUTHENTICATION_FAILED));

            TokenInfo tokenInfo = generateToken(user);

            return AuthenticateResponse.builder()
                    .token(tokenInfo.token)
                    .expires(tokenInfo.expires)
                    .build();

        }catch (Exception e){
            log.error(e.getMessage());
            throw new AppException(ErrorCode.AUTHENTICATION_FAILED);
        }
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
                    .claim("scope", buildScope(user))
                    .claim("userId", user.getId())
                    .claim("email", user.getEmail())
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



    private SignedJWT verify(String token) {
        try{
            JWSVerifier verifier = new MACVerifier(jwtSecret.getBytes());
            SignedJWT signedJWT = SignedJWT.parse(token);
            boolean verified = signedJWT.verify(verifier);
            Date expiresDate= signedJWT.getJWTClaimsSet().getExpirationTime();
            String tokenId = signedJWT.getJWTClaimsSet().getJWTID();
            if ( expiresDate.before(new Date())) {
                throw new AppException(ErrorCode.AUTHENTICATION_FAILED);
            }


            if (invalidTokenService.existsInvalidToken(tokenId)) {
                throw new AppException(ErrorCode.AUTHENTICATION_FAILED);
            }
            return signedJWT;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new AppException(ErrorCode.AUTHENTICATION_FAILED);
        }
    }


    record TokenInfo(String token, Date expires) {}

    private String buildScope(User user) {
        StringJoiner scopeJoiner = new StringJoiner(" ");
        if (user.getRoles() != null) {
            user.getRoles().forEach(role -> scopeJoiner.add(role.getName()));
        }
        return scopeJoiner.toString();
    }
}

