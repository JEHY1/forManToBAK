package com.example.FORMANTO.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", updatable  =false)
    private Long cartId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "count", nullable = false)
    private int count;

    @Builder
    public Cart(Long productId, Long customerId, int count){ //cart_Id = A.I
        this.productId = productId;
        this.customerId = customerId;
        this.count = count;
    }

    public void updateSelect(int count){
        this.count = count;
    }
    public void addSelect(int count){this.count = this.count + count > 10 ? 10 : this.count + count;}
}
