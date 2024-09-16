package com.example.shopping.user.dto;

import com.example.shopping.user.entity.UserEntity;
import lombok.*;

import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String name;
    private Integer age;
    private String email;
    private String phone;
    private String profileImage;
    private String role;


    public static UserDto fromEntity(UserEntity entity) {
        return UserDto.builder()
              //  .id(entity.getId())
                .username(entity.getUsername())
                .password("********")
                .name(entity.getName())
                .age(entity.getAge())
                .email(entity.getEmail())
                .phone(entity.getPhoneNumber())
                .profileImage(entity.getProfileImage())
                .role(entity.getRole())
                .build();
    }
}
