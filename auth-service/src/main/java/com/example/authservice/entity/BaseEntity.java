package com.example.authservice.entity;

import com.example.authservice.constant.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BaseEntity {
    int id;
    LocalDate createdAt;
    LocalDate updatedAt;
    Status status;

}
