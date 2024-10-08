package com.example.shopping.Request.dto;

import com.example.shopping.Request.entity.CloseRequest;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Builder
@Data
public class CloseRequestDto {
    private String shopName;
    private String owner;

    private String reason;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;

    public static CloseRequestDto formEntity(CloseRequest entity){
        return CloseRequestDto.builder()
                .shopName(entity.getShop().getShopName())
                .owner(entity.getShop().getUser().getUsername())
                .reason(entity.getReason())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .processedAt(entity.getProcessedAt())
                .build();
    }



}
