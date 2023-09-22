package com.example.ecommerceapplication.repository;

import com.example.ecommerceapplication.entitites.LocalUser;
import com.example.ecommerceapplication.entitites.ProductOrder;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ProductOrderRepository extends ListCrudRepository<ProductOrder, Long> {
    List<ProductOrder> findByUser(LocalUser user);
}
