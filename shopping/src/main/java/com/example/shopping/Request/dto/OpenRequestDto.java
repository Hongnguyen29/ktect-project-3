package com.example.shopping.Request.dto;

import com.example.shopping.ItemCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class OpenRequestDto {

    private String shopName;
    private String description;
    @Enumerated(EnumType.STRING)
    private ItemCategory category;
  //  private RequestStatus requestStatus;


}
