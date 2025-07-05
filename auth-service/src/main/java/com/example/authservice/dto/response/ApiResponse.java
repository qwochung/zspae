package com.example.authservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
public class ApiResponse<T> {
    @Builder.Default
    int status = 200;
    String message;
    T result;



}
