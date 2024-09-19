package com.example.shopping.shop.dto;

import com.example.shopping.shop.entity.Product;
import com.example.shopping.ItemCategory;
import com.example.shopping.shop.entity.ShopEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class ShopDto {
    private Long id;
    private String shopName;
    private String description;
    private String shopStatus;

    private ItemCategory category;
    private String owner;
    private String email;
    private String phoneNumber;
 //   private List<Product> products;

    public static ShopDto fromEntity(ShopEntity entity){


        return ShopDto.builder()
                .id(entity.getId())
                .shopName(entity.getShopName())
                .description(entity.getDescription())
                .owner(entity.getUser().getUsername())
                .category(entity.getCategory())
                .shopStatus(entity.getShopStatus())
                .email(entity.getUser().getEmail())
                .phoneNumber(entity.getUser().getPhoneNumber())
                .build();
    }




}
