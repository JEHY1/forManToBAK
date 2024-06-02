package com.example.FORMANTO.dto;


import lombok.*;


@Data
@Getter
//@AllArgsConstructor
@ToString
public class ImgForm {

    private Long img_id;
    private Long product_group_id;
    private String src;


    @Builder
    public ImgForm(Long img_id, Long product_group_id, String src){
        this.img_id = img_id;
        this.product_group_id = product_group_id;
        this.src = src;
    }
}