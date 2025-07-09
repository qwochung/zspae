package com.example.profileservice.dto.response;

import com.example.profileservice.contants.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class UserResponse {
    Long id;
    String name;
    String email;
    String phone;
    Date dob;
    Gender gender;


}
