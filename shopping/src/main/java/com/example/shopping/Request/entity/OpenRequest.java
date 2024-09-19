package com.example.shopping.Request.entity;

import com.example.shopping.BaseEntity;
import com.example.shopping.ItemCategory;
import com.example.shopping.user.entity.UserEntity;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

//@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@NoArgsConstructor

public class OpenRequest extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String shopName;
    private String description;

    @Enumerated(EnumType.STRING)
    private ItemCategory category;


    private String requestStatus; // PENDING, ACCEPTED, REJECTED
    private String rejectionReason;

  //  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime processedAt;
}
