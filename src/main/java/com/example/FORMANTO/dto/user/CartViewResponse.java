package com.example.FORMANTO.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class CartViewResponse {

    private Long cartId;
    private Long productId;
    private int count;
    private String productName;
    private String company;
    private int price;
    private Long customerId;

    @Builder
    public CartViewResponse(Long cartId, String productName, Long productId, String company, int price, int count, Long customerId){
        this.cartId = cartId;
        this.productName = productName;
        this.productId = productId;
        this.company = company;
        this.price = price;
        this.count = count;
        this.customerId = customerId;
    }
}
