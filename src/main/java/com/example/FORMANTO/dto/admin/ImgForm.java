package com.example.FORMANTO.dto.admin;


import lombok.*;

import java.util.List;

@Getter @Setter @ToString @Data

public class ImgForm {

    private Long img_id;
    private String src;
    private Long product_group_id;

    @Builder
    public ImgForm (Long img_id, String src, Long product_group_id) {

        this.img_id = img_id;
        this.src = src;
        this.product_group_id = product_group_id;
    }

    // DTo  -> 파라미터로 넘기기
//    private List<>
}
