package com.example.FORMANTO.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_tb")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", updatable = false)
    private Long paymentId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "payment_price", nullable = false)
    private int paymentPrice;

    @Column(name = "payment_type", nullable = false)
    private String paymentType;

    @CreatedDate
    @Column(name = "day", nullable = false)
    private LocalDateTime day;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "delivery_fee", nullable = false)
    private int deliveryFee;

    @Column(name = "receiver", nullable = false)
    private String receiver;

    @Column(name = "receiver_phone", nullable = false)
    private String receiverPhone;

    @Builder
    public Payment(Long customerId, int paymentPrice, String paymentType, String address, String status, int deliveryFee, String receiver, String receiverPhone) {
        this.customerId = customerId;
        this.paymentPrice = paymentPrice;
        this.paymentType = paymentType;
        this.address = address;
        this.status = status;
        this.deliveryFee = deliveryFee;
        this.receiver = receiver;
        this.receiverPhone = receiverPhone;
    }
}
