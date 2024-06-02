package com.example.FORMANTO.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Table(name = "wish")
public class Wish {

    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "customer_id",updatable = false)
//    private Long customerId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_id",updatable = false)
    private Long wishId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_group_id", nullable = false)
    private ProductGroup productGroup;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
//    @Column(name = "product_group_id", nullable = false)
//    private Long productGroupId;


    @Builder
    public Wish(ProductGroup productGroup, Customer customer, Long wishId) {
        this.wishId = wishId;
        this.productGroup = productGroup;
        this.customer = customer;
    }
}