package com.example.FORMANTO.dto.admin;


import lombok.*;

@Getter @Setter @ToString @Data

public class ProductForm {

    private Long product_id;
    private Long product_group_id;
    private int price;
    private int quantity;
    private String nameP;
    private String img_src;

    @Builder
    public ProductForm (Long product_id, Long product_group_id, int price,
                        int quantity, String nameP, String img_src) {

        this.product_id = product_id;
        this.product_group_id = product_group_id;
        this.price = price;
        this.quantity = quantity;
        this.nameP = nameP;
        this.img_src = img_src;
    }

}
