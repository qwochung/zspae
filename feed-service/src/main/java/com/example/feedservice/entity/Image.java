package com.example.feedservice.entity;

import com.example.feedservice.constant.Status;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "image")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Image extends BaseEntity{
    String fileName;
    String url;
    String objectKey;
    String ownerId;
    String contentType;
    Long size;
    Status status;
}
