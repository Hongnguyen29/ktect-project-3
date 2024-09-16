package com.example.shopping.openRequest.dto;

import lombok.Data;
import lombok.Getter;

@Getter
public class InspectDto {
    private boolean approved;
    private String rejectionReason;
}
