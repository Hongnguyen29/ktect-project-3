package com.example.shopping.product.entity;

import com.example.shopping.BaseEntity;
import com.example.shopping.order.entity.OrderItem;
import com.example.shopping.shop.entity.ItemCategory;
import com.example.shopping.shop.entity.ShopEntity;
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
    private Double price;
    private Integer stock;
    private String description;

    @Enumerated(EnumType.STRING)
    private final List<ItemCategory> categories = new ArrayList<>();

    @OneToOne(mappedBy = "product")
    private OrderItem orderItem;


}
