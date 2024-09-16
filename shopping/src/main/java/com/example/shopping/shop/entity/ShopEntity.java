package com.example.shopping.shop.entity;

import com.example.shopping.BaseEntity;
import com.example.shopping.product.entity.Product;
import com.example.shopping.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor

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

   /* @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private final List<Product> products = new ArrayList<>();

    */

}
