package com.example.FORMANTO.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment_detail_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString

public class PaymentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_detail_id")
    private Long paymentDetailId;

    @Column(name = "sale_product_id", nullable = false)
    private Long saleProductId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "product_price", nullable = false)
    private int productPrice;

    @Builder
    public PaymentDetail(Long saleProductId, String status, int productPrice) {
        this.saleProductId = saleProductId;
        this.status = status;
        this.productPrice = productPrice;
    }
}
