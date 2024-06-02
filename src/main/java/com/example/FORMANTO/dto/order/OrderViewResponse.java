package com.example.FORMANTO.dto.order;

import com.example.FORMANTO.domain.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderViewResponse {

    private Long productId;
    private String name;
    private String imgSrc;
    private int price;
    private int count;

    public OrderViewResponse(Product product, int count){
        this.productId = product.getProductId();
        this.name = product.getName();
        this.imgSrc = product.getImgSrc();
        this.price = product.getPrice();
        this.count = count;
    }
}
