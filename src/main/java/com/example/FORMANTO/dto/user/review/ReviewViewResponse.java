package com.example.FORMANTO.dto;

import lombok.*;

import java.util.List;
//상세페이지의 리뷰  db->view로 데이터 불러오기
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString

public class ReviewViewResponse {
    //response가 db(서버)-> 클라이언트에게
    private String username;
    private String content;
    private List<String> imgs;
    private Long reviewId;//리뷰 유무


    @Builder
    public ReviewViewResponse(String username, String content, List<String> imgs,Long reviewId) {
        this.username = username;
        this.content = content;
        this.imgs = imgs;
        this.reviewId = reviewId;
    }
}