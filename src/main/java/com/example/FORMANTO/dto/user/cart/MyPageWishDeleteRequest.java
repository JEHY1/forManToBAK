package com.example.FORMANTO.dto.user.wish;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MyPageWishDeleteRequest {
    //0528
    //wish.html-> db로 체크박스시 삭제할 데이터 담기

    private List<Long> wishIds;
    private List<Long> productGroupIds;
    private List<Long> customerIds;

}