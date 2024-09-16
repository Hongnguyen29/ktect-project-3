package com.example.shopping.openRequest;

import com.example.shopping.AuthenticationFacade;
import com.example.shopping.openRequest.dto.InspectDto;
import com.example.shopping.openRequest.dto.OpenRequestDto;
import com.example.shopping.openRequest.dto.OpenRequestView;
import com.example.shopping.shop.dto.ShopDto;
import com.example.shopping.shop.entity.ItemCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("openRequest")

public class OpenRequestController {
    private final OpenRequestService service;
    private final AuthenticationFacade authentication;


    @PostMapping("/{username}")    // nguoi dung gui yc mo shop
    public ResponseEntity<?> openShop(
            @PathVariable String username,
            @RequestBody OpenRequestDto dto) {
        String username1 = authentication.findUsername();

        if (!username1.equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You are not authorized to perform this action.");
        }
        try {
            if (dto.getShopName() == null
                    || dto.getDescription() ==null
                    ||dto.getCategory() ==null ) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Failed to create OpenRequest due to invalid data.");
            }
            OpenRequestView openRequestView = service.openShop(username1, dto);
            return ResponseEntity.ok(openRequestView);
        } catch (IllegalArgumentException e) {
            // Trả về lỗi nếu dữ liệu bị thiếu
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Xử lý lỗi không xác định
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }
    @GetMapping("/{username}/{requestId}")
    public ResponseEntity<?> readOneRequest (
            @PathVariable
            Long requestId,
            @PathVariable
            String username
    ){
        String username1 = authentication.findUsername();
        try {
            if (!username1.equals(username)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You are not authorized to perform this action.");
            }
            OpenRequestView openRequestView = service.readOneRequest(requestId,username1);
            return ResponseEntity.ok(openRequestView);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
    @GetMapping("/admin/readAll")
    public List<OpenRequestView> readAllRequest () {
        String username = authentication.findUsername();
        if(username.equals("admin")) {return service.readAllRequest();}
        return null;
    }


    @PostMapping("/admin/confirm/{requestId}")
    public ResponseEntity<?> confirmRequest(
            @PathVariable Long requestId,
            @RequestBody InspectDto inspectDto
    ) {
        String username = authentication.findUsername();
        if (!username.equals("admin")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You are not authorized to perform this action.");
        }

        try {
            OpenRequestView updatedRequest = service.openConfirm(requestId, inspectDto);
            return ResponseEntity.ok(updatedRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
    /*
    @PostMapping("/admin/{requestId}/confirm")
    public ResponseEntity<?> openConfirm (
            @PathVariable
            Long requestId,
            @RequestParam
            boolean approved,
            @RequestParam
            String rejectionReason
    ) {
        String  username1 = authentication.findUsername();

            OpenRequestView openRequestView  =    service.openConfirm(requestId, approved, rejectionReason);
            return ResponseEntity.ok(openRequestView);

    }

     */
}
