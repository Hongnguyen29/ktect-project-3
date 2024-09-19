package com.example.shopping.Request.entity;

import com.example.shopping.BaseEntity;
import com.example.shopping.shop.entity.ShopEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class CloseRequest extends BaseEntity {


    @OneToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;

    private String status; // PENDING, ACCEPTED, REJECTED

    private String reason;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime processedAt;
}