package com.example.shopping.shop.dto;

import com.example.shopping.shop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private Long id;
    private String nameItem;
    private String image;
    private Integer price;
    private Integer stock;
    private String description;


    public static ProductDto fromEntity(Product entity){
        return ProductDto.builder()
                .id(entity.getId())
                .nameItem(entity.getNameItem())
                .image(entity.getImage())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .description(entity.getDescription())
                .build();
    }




}
