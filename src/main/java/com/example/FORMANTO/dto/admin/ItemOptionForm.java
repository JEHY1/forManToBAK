package com.example.FORMANTO.dto;

import lombok.*;

@Data
@Getter
//@AllArgsConstructor
@ToString
public class ItemOptionForm {

    private Long product_id;
    private Long product_group_id2;
    private int price_option;
    private int quantity;
    private String name_option;
//    private String img_src;


    @Builder
    public ItemOptionForm (Long product_id, Long product_group_id2, int price_option, int quantity, String name_option){
        this.product_id = product_id;
        this.product_group_id2 = product_group_id2;
        this.price_option = price_option;
        this.quantity = quantity;
        this.name_option = name_option;
    }

}