package com.example.shopping.user.entity;

import com.example.shopping.BaseEntity;
import com.example.shopping.order.entity.OrderItem;
import com.example.shopping.shop.entity.ShopEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table")
@Entity
@Builder
public class UserEntity extends BaseEntity {

    private String username;
    private String password;
    private String name;
    private Integer age;
    private String email;
    private String phoneNumber;
    private String role; // INACTIVE, USER, BUSINESS, ADMIN
    private String profileImage;


/*    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "userProfile_id")
    private UserProfile userProfile;*/

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ShopEntity shop;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private final List<OrderItem> orderList = new ArrayList<>();




}
