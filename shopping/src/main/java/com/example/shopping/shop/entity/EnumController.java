package com.example.shopping.shop.entity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumController {
    @GetMapping("/itemCategory")
    public ResponseEntity<List<String>> getCategories() {
        // Chuyển đổi tất cả các giá trị enum thành danh sách chuỗi
        List<String> categories = Arrays.stream(ItemCategory.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categories);}
}
