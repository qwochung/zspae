package com.example.profileservice.entity;

import com.example.profileservice.contants.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.neo4j.core.schema.Id;

@Data
@SuperBuilder
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    @Id
    Long id;
    Long createdAt;
    Long updatedAt;
    @Enumerated(EnumType.STRING)
    Status status;

}
