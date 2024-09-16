package com.example.shopping.order.entity;

import com.example.shopping.BaseEntity;
import com.example.shopping.product.entity.Product;
import com.example.shopping.user.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderItem extends BaseEntity {
    private Integer quantity;
    private String status;
    private LocalDateTime orderTime;
    private Integer totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;




}
