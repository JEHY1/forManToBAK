package com.example.FORMANTO.dto;
//db-> view로 데이터 뿌리는 dto

import com.example.FORMANTO.dto.user.wish.MyPageWishOptionResponse;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@ToString
public class WishResponse {


    private String company;
    private String name;
    private int price;
    private String src;

    //db로 보낼때 필요한 데이터들  customer는 controller에서 보내준다는 가정하에
    private Long wishId;
    private Long productGroupId;

    //0530추가 wish->cart로 옮길때 옵션 선택을 위한 필드
    private List<MyPageWishOptionResponse> productInfos;

    @Builder
    public WishResponse(String company, String name, int price, String src, Long wishId, Long productGroupId, List<MyPageWishOptionResponse> productInfos) {
        this.company = company;
        this.name = name;
        this.price = price;
        this.src = src;
        this.wishId = wishId;
        this.productGroupId = productGroupId;
        this.productInfos = productInfos;
    }



}