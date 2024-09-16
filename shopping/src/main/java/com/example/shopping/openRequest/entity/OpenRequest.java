package com.example.shopping.openRequest.entity;

import com.example.shopping.BaseEntity;
import com.example.shopping.RequestStatus;
import com.example.shopping.shop.entity.ItemCategory;
import com.example.shopping.user.entity.UserEntity;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter

public class OpenRequest extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String shopName;
    private String description;

    @Enumerated(EnumType.STRING)
    private ItemCategory category;


    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus; // PENDING, ACCEPTED, REJECTED
    private String rejectionReason;

  //  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime processedAt;
}
