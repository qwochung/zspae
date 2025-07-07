package com.example.authservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity(name ="invalid_token")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvalidToken {
    @Id
    String id;
    Date expiresTime;

}
