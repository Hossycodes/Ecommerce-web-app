package com.example.ecommerceapplication.entitites;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "address_line1", nullable = false, length = 526)
    private String addressLine1;
    @Column(name = "address_line2", length = 526)
    private String addressLine2;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "country", nullable = false)
    private String country;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private LocalUser user;
//    @OneToMany(mappedBy = "address", cascade = CascadeType.REMOVE, orphanRemoval = true)
//    private List<ProductOrder> productOrders = new ArrayList<>();

}
