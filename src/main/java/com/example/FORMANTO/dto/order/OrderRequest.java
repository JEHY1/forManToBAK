package com.example.FORMANTO.dto.order;

import com.example.FORMANTO.controller.JoinViewController;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private List<Long> productIds;
    private List<Integer> counts;
    private int totalPrice;
    private List<Long> cartIds;

}
