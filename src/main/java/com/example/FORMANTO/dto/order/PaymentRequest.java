package com.example.FORMANTO.dto.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PaymentRequest {

    private int paymentPrice;
    private String paymentType;
    private int deliveryFee;
    private String postalCode;
    private String roadNameAddress;
    private String detailAddress;
    private List<Long> productIds;
    private List<Integer> productCounts;
    private String receiver;
    private String receiverPhone;
    private List<Long> cartIds;


}
