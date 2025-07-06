package com.example.authservice.entity;

import com.example.authservice.constant.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    String name;
    String description;



}
