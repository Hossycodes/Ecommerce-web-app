package com.example.ecommerceapplication.controller;

import com.example.ecommerceapplication.entitites.LocalUser;
import com.example.ecommerceapplication.entitites.ProductOrder;
import com.example.ecommerceapplication.service.implementation.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final ProductOrderService productOrderService;

    @GetMapping("/product/{id}")
    public List<ProductOrder> getOrders(@AuthenticationPrincipal LocalUser user, Long id){
        return productOrderService.getOrders(user, id);
    }
}
