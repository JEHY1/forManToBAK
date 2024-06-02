package com.example.FORMANTO.dto.user.cart;

import lombok.*;

import java.util.List;

//0528
//장바구니에서 체크 후 삭제버튼 누르면 삭제됌
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MyPageCartDeleteRequest {

    private List<Long> cartIds;

}