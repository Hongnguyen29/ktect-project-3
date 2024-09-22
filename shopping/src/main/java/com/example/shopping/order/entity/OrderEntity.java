package com.example.shopping.order.entity;

import com.example.shopping.BaseEntity;
import com.example.shopping.shop.entity.Product;
import com.example.shopping.shop.entity.ShopEntity;
import com.example.shopping.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderEntity extends BaseEntity {

    private Integer quantity;
    private String status;
    private LocalDateTime orderTime;
    private Integer totalMoney;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;






}
