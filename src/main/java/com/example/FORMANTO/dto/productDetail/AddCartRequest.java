package com.example.FORMANTO.dto.productDetail;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddCartRequest {

    private List<Long> productIds;
    private List<Integer> counts;

}
