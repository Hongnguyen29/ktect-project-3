package com.example.shopping.shop.entity;

import com.example.shopping.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

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

   /* @OneToOne(mappedBy = "product")
    private OrderItem orderItem;*/

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;


}
