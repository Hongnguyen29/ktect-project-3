package com.example.shopping.openRequest.dto;

import com.example.shopping.shop.entity.ItemCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OpenRequestDto {

    private String shopName;
    private String description;
    @Enumerated(EnumType.STRING)
    private ItemCategory category;
  //  private RequestStatus requestStatus;


}
