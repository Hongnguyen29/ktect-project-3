package com.example.shopping.shop;

import com.example.shopping.AuthenticationFacade;
import com.example.shopping.ItemCategory;
import com.example.shopping.Request.dto.OpenRequestDto;
import com.example.shopping.Request.dto.OpenRequestView;
import com.example.shopping.shop.dto.ProductDto;
import com.example.shopping.shop.dto.ProductViewDto;
import com.example.shopping.shop.dto.ShopDto;
import com.example.shopping.shop.entity.ShopEntity;
import com.example.shopping.shop.repo.ShopRepository;
import com.example.shopping.user.CustomUserDetailsService;
import com.example.shopping.user.entity.UserEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
//@RequestMapping("shops")
public class ShopController {
    private final ShopService shopService;
    private final AuthenticationFacade facade;

    @PutMapping("/shop/update")
    public ResponseEntity<?> infoUpdate(
             @RequestBody OpenRequestDto dto
    ){
        UserEntity user = facade.findUser();
        try {
            ShopDto shopDto = shopService.updateInfoShop(dto,user);
            return ResponseEntity.ok(shopDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
    @PostMapping("/shop/product")
    public ResponseEntity<?> addProduct(
            @RequestParam String nameItem,
            @RequestParam Integer price,
            @RequestParam Integer stock,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) MultipartFile image
    ){
        UserEntity user = facade.findUser();
        Long shopId = user.getShop().getId();
        ProductDto dto = new ProductDto();
        dto.setNameItem(nameItem);
        dto.setPrice(price);
        dto.setStock(stock);
        dto.setDescription(description);
        try {
            ProductDto newProduct = shopService.addProduct(shopId,dto,image);
            return ResponseEntity.ok(newProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
    @PutMapping("/shop/{productId}")
    public ProductDto updateProduct(
            @PathVariable  Long productId,
            @RequestParam(required = false) String nameItem,
            @RequestParam(required = false) Integer price,
            @RequestParam(required = false) Integer stock,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) MultipartFile image
    ){
        UserEntity user = facade.findUser();
        Long shopId = user.getShop().getId();
        ProductDto dto = new ProductDto();
        dto.setNameItem(nameItem);
        dto.setPrice(price);
        dto.setStock(stock);
        dto.setDescription(description);

        return  shopService.updateProduct(shopId,productId,dto,image);
    }
    @DeleteMapping("/shop/{productId}")
    public String deleteProduct(
            @PathVariable
            Long productId
    ){
        UserEntity user = facade.findUser();
        Long shopId = user.getShop().getId();
        return shopService.deleteProduct(shopId,productId);
    }
    @GetMapping("/view/shops")
    public List<ShopDto> shops(
        @RequestParam(name = "nameShop", required = false)
        String shopName,
        @RequestParam(name = "category", required = false)
        ItemCategory category
    ){
        return shopService.shopsView(shopName,category);
    }
    @GetMapping("/view/products")
    public List<ProductViewDto> productsView(
            @RequestParam(name = "nameItem",required = false )
            String nameItem,
            @RequestParam(name ="minPrice", defaultValue = "0")
            Integer minPrice,
            @RequestParam(name = "maxPrice",defaultValue = "100000000")
            Integer maxPrice
    ){
        return shopService.productsView(nameItem,minPrice,maxPrice);
    }





}
