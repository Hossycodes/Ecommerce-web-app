package com.example.ecommerceapplication.service.implementation;

import com.example.ecommerceapplication.entitites.LocalUser;
import com.example.ecommerceapplication.entitites.ProductOrder;
import com.example.ecommerceapplication.repository.ProductOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOrderService {
    private final ProductOrderRepository productOrderRepository;

    public List<ProductOrder> getOrders(LocalUser user, Long id){
        return productOrderRepository.findByUser(user);
    }
}
