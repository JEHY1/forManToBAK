package com.example.FORMANTO.dto;

import lombok.Data;

import java.util.List;
//세정 장바구니

//customer id 장바구니->주문으로 넘길땐 안 넘겼는데 어케 구분함?
//hidden으로 다 넘기는 방법 말고 다른 방법은 없을까
//엔티티에서 setter안하니까 바꿀 수ㅏㄱ 없는데    jpa db에서 업데이트 하는 방법
//리파지토리에서 findby~~처럼 형식이 있는건지 물어보기     서비스 계층에서 변경하는 건지 update기능 자체가 있는건지
@Data
public class cartSelectChangeResponse {
    //하나의 제품의 수량을 변경시의 dto

    private List<Long> productId;
    private List<Integer> count; // 변경할 상품 수량
    private List<Long> customerId;
    private List<Integer> countOri; // 기존 상품 수량

}