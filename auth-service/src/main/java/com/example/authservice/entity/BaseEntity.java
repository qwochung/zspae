package com.example.authservice.entity;

import com.example.authservice.constant.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long createdAt;
    Long updatedAt;
    @Enumerated(EnumType.STRING)
    Status status;

}
