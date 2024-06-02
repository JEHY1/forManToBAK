package com.example.FORMANTO.dto.user.orderCancel;

import lombok.Builder;
import lombok.Data;

@Data
public class OrderCancelViewResponse {

    private Long orderNum;//주문번호
    private int count;//수량
    private int price;//가격
    private String name;//제품이름
    private String cancel;//취소여부  -->어디서 얻지???

    @Builder
    public OrderCancelViewResponse(Long orderNum, int count, int price, String name, String cancel) {
        this.orderNum = orderNum;
        this.count = count;
        this.price = price;
        this.name = name;
        this.cancel = cancel;
    }


}
