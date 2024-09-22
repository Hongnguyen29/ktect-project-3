package com.example.shopping.order;

import com.example.shopping.order.dto.OrderViewDto;
import com.example.shopping.order.entity.OrderEntity;
import com.example.shopping.order.repo.OrderRepository;
import com.example.shopping.shop.entity.Product;
import com.example.shopping.shop.entity.ShopEntity;
import com.example.shopping.shop.repo.ProductRepository;
import com.example.shopping.user.entity.UserEntity;
import com.example.shopping.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public OrderViewDto createOrder(
            Long productId,
            String username,
            Integer quantity
    ){
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        ShopEntity shop = product.getShop();

        OrderEntity order = new OrderEntity();

        if(quantity > product.getStock()){
            throw new IllegalArgumentException("Error: Order quantity cannot exceed stock quantity.");
        }
        order.setUser(user);
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setTotalMoney(quantity*product.getPrice());
        order.setStatus("PENDING");
        order.setOrderTime(LocalDateTime.now());
        order.setShop(shop);
        orderRepository.save(order);
        return OrderViewDto.fromEntity(order);
    }
    public OrderViewDto cancelOrder(
            String username,
            Long orderId
    ){
        OrderEntity order = orderRepository.findById(orderId).orElseThrow();
        String username1 = order.getUser().getUsername();
        if (!(username1.equals(username))) {
            throw new IllegalArgumentException(
                    "Cannot access this content.");
        }
        if(order.getStatus().equals("PENDING")){
            order.setStatus("CANCEL");
            orderRepository.save(order);
        }
        return OrderViewDto.fromEntity(order);
    }
    public OrderViewDto acceptOrder(
            Long orderId,
            String username
    ){
        OrderEntity order = orderRepository.findById(orderId).orElseThrow();
        Product product = order.getProduct();
        String username1 = order.getShop().getUser().getUsername();

        if(!(username.equals(username1))){
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("hihi");
        }
        Integer stock = product.getStock();
        if(order.getStatus().equals("PENDING")){
            order.setStatus("IN TRANSIT");
            orderRepository.save(order);
            product.setStock(stock - order.getQuantity());
            productRepository.save(product);
        }
        return OrderViewDto.fromEntity(order);

    }

    public OrderViewDto viewOneOrder(
            Long orderId,
           String username ){
        OrderEntity order = orderRepository.findById(orderId).orElseThrow();
        String username1 = order.getUser().getUsername();
        ShopEntity shop = order.getShop();
        if (!((username1.equals(username) ||
                username.equals(shop.getUser().getUsername())))) {
            throw new IllegalArgumentException(
                    "Cannot view this content.");
        }
        return OrderViewDto.fromEntity(order);
    }

    public List<OrderViewDto> userView(
            UserEntity user
    ){
        List<OrderViewDto> orderViews = new ArrayList<>();
        List<OrderEntity> orderList =  orderRepository.findOrdersByUserIdOrderedByTime(user.getId());
        for (OrderEntity o : orderList){
            orderViews.add(OrderViewDto.fromEntity(o));
        }
        return orderViews;
    }
    public List<OrderViewDto> shopView(
            UserEntity user
    ){
        List<OrderViewDto> orderViews = new ArrayList<>();
        ShopEntity shop = user.getShop();
        List<OrderEntity> orderList =  orderRepository.findOrdersByShopIdOrdered(shop.getId());
        for (OrderEntity o : orderList){
            orderViews.add(OrderViewDto.fromEntity(o));
        }
        return orderViews;
    }





}
