package com.example.authservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AuthenticateResponse {
    String token;
    Date expires;
}
