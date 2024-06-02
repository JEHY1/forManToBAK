package com.example.FORMANTO.dto.user.orderInquiry;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
//0528
public class MyPageOrderInquiryResponse {
    private LocalDateTime dateTime;
    private String name;
    private String company;
    private int count;
    private int price;
    private String status;
    private String src;  //사진을 어케 하까

    @Builder
    public MyPageOrderInquiryResponse(LocalDateTime dateTime, String name, String company, int count, int price, String status) {
        this.dateTime = dateTime;
        this.name = name;
        this.company = company;
        this.count = count;
        this.price = price;
        this.status = status;
    }
}
