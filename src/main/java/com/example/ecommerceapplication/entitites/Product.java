package com.example.ecommerceapplication.entitites;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name",nullable = false, unique = true)
    private String name;

    private String shortDescription;
    @Column(name = "long_description", nullable = false)
    private String longDescription;
    @Column(name = "price", nullable = false)
    private Double price;
    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Inventory inventory;

}
