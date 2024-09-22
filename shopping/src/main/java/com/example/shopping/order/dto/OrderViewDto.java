package com.example.shopping.order.dto;

import com.example.shopping.order.entity.OrderEntity;
import com.example.shopping.shop.entity.Product;
import com.example.shopping.user.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderViewDto {
    private Long orderId;
    private String nameItem;
    private Integer quantity;
    private Integer totalMoney;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;
    private Long productId;
    private String shopName;

    public static OrderViewDto fromEntity(OrderEntity entity){
        return OrderViewDto.builder()
                .orderId(entity.getId())
                .nameItem(entity.getProduct().getNameItem())
                .quantity(entity.getQuantity())
                .totalMoney(entity.getTotalMoney())
                .status(entity.getStatus())
                .orderTime(entity.getOrderTime())
                .productId(entity.getProduct().getId())
                .shopName(entity.getShop().getShopName())
                .build();
    }

}
