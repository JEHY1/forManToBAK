package com.example.FORMANTO.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", updatable = false)
    private Long productId;

    @Column(name = "product_group_id", nullable = false)
    private Long productGroupId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "img_src", nullable = false)
    private String imgSrc;


    @Builder
    public Product(Long productGroupId, String name, int price, int quantity, String imgSrc) { //product_id = A.I
        this.productGroupId = productGroupId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imgSrc = imgSrc;
    }

    public void updateQuantity(int count){
        this.quantity -= count;
    }
}
