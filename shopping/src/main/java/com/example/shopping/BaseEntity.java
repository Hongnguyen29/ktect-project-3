package com.example.shopping;

import jakarta.persistence.*;
import lombok.Getter;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
// "ROLE_USER,ORDER,READ.REQUEST"
//"ROLE_BUSINESS,READ.REQUEST,ACCEPT"
//"ROLE_ADMIN,READ.REQUEST"
//"ROLE_INACTIVE"


// INACTIVE, USER, BUSINESS, ADMIN
//  PENDING,
//    ACCEPTED,
//    REJECTED