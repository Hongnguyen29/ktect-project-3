package com.example.shopping.openRequest;

import com.example.shopping.RequestStatus;
import com.example.shopping.openRequest.dto.InspectDto;
import com.example.shopping.openRequest.dto.OpenRequestDto;
import com.example.shopping.openRequest.dto.OpenRequestView;
import com.example.shopping.openRequest.entity.OpenRequest;
import com.example.shopping.closeRequest.repo.CloseRequestRepository;
import com.example.shopping.shop.dto.ShopDto;
import com.example.shopping.shop.entity.ItemCategory;
import com.example.shopping.shop.entity.ShopEntity;
import com.example.shopping.openRequest.repo.OpenRequestRepository;
import com.example.shopping.shop.repo.ShopRepository;
import com.example.shopping.user.entity.UserEntity;
import com.example.shopping.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.shopping.openRequest.dto.OpenRequestView.fromEntity;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpenRequestService {
    private final ShopRepository shopRepository;
    private final OpenRequestRepository openRepository;
    private final CloseRequestRepository closureRepository;
    private final UserRepository userRepository;


    public OpenRequestView openShop(
            String username,
            OpenRequestDto openDto){
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        OpenRequest openRequest1 = new OpenRequest();

        openRequest1.setUser(user);
        openRequest1.setShopName(openDto.getShopName());
        openRequest1.setDescription(openDto.getDescription());
        openRequest1.setCategory(openDto.getCategory());
        openRequest1.setRequestStatus(RequestStatus.PENDING);
        openRequest1.setCreatedAt(LocalDateTime.now());
        openRepository.save(openRequest1);
        return fromEntity(openRequest1);
    }
    public OpenRequestView readOneRequest(
            Long requestId,
            String username1
    ){
        OpenRequest openRequest= openRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Resquest not found"));
        String username2 =  openRequest.getUser().getUsername();
        if(!(username1.equals("admin") || username1.equals(username2))){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return  OpenRequestView.fromEntity(openRequest);
    }
    public List<OpenRequestView> readAllRequest(){
        List<OpenRequest> openRequests = openRepository.findAllOrderedByStatusAndCreatedAt();
        List<OpenRequestView> openRequestViews = new ArrayList<>();
        for (OpenRequest o : openRequests){
            openRequestViews.add(OpenRequestView.fromEntity(o));
        }
        return openRequestViews;
    }
    public OpenRequestView openConfirm(
            Long requestId,
            InspectDto inspectDto
    ) {
        // Tìm kiếm yêu cầu mở cửa hàng
        OpenRequest openRequest = openRepository.findById(requestId)
                .orElseThrow();

        UserEntity user = openRequest.getUser();


        if (inspectDto.isApproved()) {
            // Xử lý khi yêu cầu được chấp nhận
            openRequest.setRequestStatus(RequestStatus.ACCEPTED);
            openRequest.setProcessedAt(LocalDateTime.now());
            openRepository.save(openRequest);

            if (openRequest.getRequestStatus().equals(RequestStatus.ACCEPTED)) {
                // Cập nhật vai trò của người dùng
                String updatedRoles = "ROLE_BUSINESS,READ.REQUEST,CLOSE.REQUEST,ORDER.CONFIRM";
                user.setRole(updatedRoles);
                userRepository.save(user);

                ShopEntity shop = user.getShop();
                // Cập nhật thông tin cửa hàng
                shop.setShopName(openRequest.getShopName());
                shop.setDescription(openRequest.getDescription());
                shop.setShopStatus("ACTIVE");
                shop.setUser(user);
                shop.setCategory(openRequest.getCategory());
                shopRepository.save(shop);
            }
        } else {
            // Xử lý khi yêu cầu bị từ chối
            if (inspectDto.getRejectionReason() != null) {
                openRequest.setRejectionReason(inspectDto.getRejectionReason());
            }
            openRequest.setRequestStatus(RequestStatus.REJECTED);
            openRepository.save(openRequest);
        }
        return  OpenRequestView.fromEntity(openRequest);

        // Trả về thông tin yêu cầu đã được cập nhật

    }






}
