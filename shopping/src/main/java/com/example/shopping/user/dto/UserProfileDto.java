package com.example.shopping.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class UserProfileDto {
    private String name;
    private Integer age;
    private String email;
    private String phoneNumber;

}
