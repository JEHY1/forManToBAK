package com.example.FORMANTO.dto.productDetail;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ReviewViewResponse {

    private String username;
    private String content;
    private List<String> imgs;

    @Builder
    public ReviewViewResponse(String username, String content, List<String> imgs) {
        this.username = username;
        this.content = content;
        this.imgs = imgs;
    }
}
