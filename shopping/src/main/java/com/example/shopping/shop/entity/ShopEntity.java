package com.example.shopping.shop.entity;

import com.example.shopping.BaseEntity;
import com.example.shopping.ItemCategory;
import com.example.shopping.order.entity.OrderEntity;
import com.example.shopping.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopEntity extends BaseEntity {


    private String shopName;
    private String description;
    private String shopStatus; // Ví dụ: PENDING, OPEN, CLOSED

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user; //owner

    @Enumerated(EnumType.STRING)
    private ItemCategory category;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private final List<Product> products = new ArrayList<>();
    @OneToMany(mappedBy = "shop",cascade = CascadeType.ALL)
    private final List<OrderEntity> orders = new ArrayList<>();


}
