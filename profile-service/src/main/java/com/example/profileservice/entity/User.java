package com.example.profileservice.entity;

import com.example.profileservice.contants.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Date;

@Node(value = "user")
@Data
@SuperBuilder

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {
    String name;
    String email;
    String phone;
    Date dob;
    Gender gender;

    //    Long imageId;
    //    Long coverId;


}
