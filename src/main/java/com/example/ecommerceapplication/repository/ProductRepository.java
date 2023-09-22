package com.example.ecommerceapplication.repository;

import com.example.ecommerceapplication.entitites.Product;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductRepository extends ListCrudRepository<Product, Long> {
    
}
