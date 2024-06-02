package com.example.FORMANTO.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "sale_product_tb")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaleProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_product_id", updatable = false)
    private Long saleProductId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "payment_id", nullable = false)
    private Long paymentId;

    @Column(name = "total_quantity", nullable = false)
    private int totalQuantity;

    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "review_deadline", nullable = false)
    private LocalDateTime reviewDeadline;

    @Builder
    public SaleProduct(Long productId, Long paymentId, int totalQuantity, Long reviewId, LocalDateTime reviewDeadline) {
        this.productId = productId;
        this.paymentId = paymentId;
        this.totalQuantity = totalQuantity;
        this.reviewId = reviewId;
        this.reviewDeadline = reviewDeadline;
    }


}
