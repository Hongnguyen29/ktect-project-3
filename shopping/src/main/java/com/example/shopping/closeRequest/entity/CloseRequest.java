package com.example.shopping.closeRequest.entity;

import com.example.shopping.BaseEntity;
import com.example.shopping.RequestStatus;
import com.example.shopping.shop.entity.ShopEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class CloseRequest extends BaseEntity {


    @OneToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;

    @Enumerated(EnumType.STRING)
    private RequestStatus status; // PENDING, ACCEPTED, REJECTED

    private String closureReason;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
}