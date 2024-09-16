package com.example.shopping.copy;

/*

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("shops")
*/

public class ShopController {
   /* private final ShopService service;
    private final AuthenticationFacade authentication;

    @GetMapping("/itemCategory")
    public List<String> categorieList(){
        return service.listCategory();
    }
    @PostMapping("/{username}/openRequest")    // nguoi dung gui yc mo shop
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
                    ||dto.getCategories() ==null ) {
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
    @GetMapping("/{username}/openRequest/{requestId}")
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
    @GetMapping("/openRequest/readAll")
    public ResponseEntity<?> readAllRequest () {
        String username1 = authentication.findUsername();
        try {
            if (!username1.equals("admin")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("You are not authorized to perform this action.");
            }
            List<OpenRequestView> openRequestViews = service.readAllRequest();
            return ResponseEntity.ok(openRequestViews);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }*/
}
