package com.example.shopping.shop.dto;

import com.example.shopping.ItemCategory;
import com.example.shopping.shop.entity.Product;
import com.example.shopping.shop.entity.ShopEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductViewDto {
    private String nameItem;
    private String image;
    private Integer price;
    private String description;
    private String shopName;
    private Long shopId;


    public static ProductViewDto fromEntity(Product entity){
        return ProductViewDto.builder()
                .nameItem(entity.getNameItem())
                .image(entity.getImage())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .shopName(entity.getShop().getShopName())
                .shopId(entity.getShop().getId())
                .build();
    }

}
