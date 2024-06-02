package com.example.FORMANTO.dto.user.review;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ReviewViewRequest {

    private String username;
    private String content;
    private List<String> imgs;

    @Builder
    public ReviewViewRequest(String username, String content, List<String> imgs) {

    }


}