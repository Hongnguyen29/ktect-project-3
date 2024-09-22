package com.example.shopping.shop.entity;

import com.example.shopping.BaseEntity;
import com.example.shopping.order.entity.OrderEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseEntity {

    private String nameItem;
    private String image;
    private Integer price;
    private Integer stock;
    private String description;

    @OneToMany(mappedBy = "product")
    private final List<OrderEntity> orders = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;


}
