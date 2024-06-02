package com.example.FORMANTO.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

//마이페이지에서 db->view 데이터 불러오기

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MypageReviewResponse {

    //response가 db(서버)-> 클라이언트에게

    private String imgsrc;
    //내가 추가한 코드
    private String company;
    private String name;
    //작성기간도 추가하기
    private LocalDateTime deadline;
    //리뷰작성유무
    private Long reviewId;


    @Builder
    public MypageReviewResponse( String imgsrc, String company, String name,LocalDateTime deadline,Long reviewId) {
        this.imgsrc = imgsrc;
        this.company = company;
        this.name = name;
        this.deadline = deadline;
        this.reviewId = reviewId;
    }


}
