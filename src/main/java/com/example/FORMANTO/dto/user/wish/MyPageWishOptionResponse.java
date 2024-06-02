package com.example.FORMANTO.dto.user.wish;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@ToString
public class MyPageWishOptionResponse {
    //0530추가 wish->cart로 옮길때 옵션 선택을 위한 필드
    private int productPrice;
    private String productName;
    private Long productId;
    //count를 cart로 넘기기 위해서
    private int count;


    @Builder
    public MyPageWishOptionResponse(int productPrice, String productName, Long productId, int count) {
        this.productPrice = productPrice;
        this.productName = productName;
        this.productId = productId;
        this.count= count;
    }
}
