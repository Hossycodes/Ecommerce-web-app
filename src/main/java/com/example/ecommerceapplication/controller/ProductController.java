package com.example.ecommerceapplication.controller;

import com.example.ecommerceapplication.entitites.Product;
import com.example.ecommerceapplication.service.implementation.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    private List<Product> getProducts(){
        return productService.getProducts();
    }
}
