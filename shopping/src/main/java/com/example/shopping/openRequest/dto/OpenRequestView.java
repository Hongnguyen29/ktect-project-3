package com.example.shopping.openRequest.dto;

import com.example.shopping.RequestStatus;
import com.example.shopping.openRequest.entity.OpenRequest;
import com.example.shopping.shop.entity.ItemCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class OpenRequestView {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;

    private String shopName;
    private String description;
    private ItemCategory category;
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;
    private String rejectionReason;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime processedAt;

    public static OpenRequestView fromEntity(OpenRequest entity) {
        return OpenRequestView.builder()
                .id(entity.getId())
                .username(entity.getUser().getUsername())
                .email(entity.getUser().getEmail())
                .phoneNumber(entity.getUser().getPhoneNumber())
                .shopName(entity.getShopName())
                .description(entity.getDescription())
                .category(entity.getCategory())
                .requestStatus(entity.getRequestStatus())
                .rejectionReason(entity.getRejectionReason())
                .createdAt(entity.getCreatedAt())
                .processedAt(entity.getProcessedAt())
                .build();
    }


}
