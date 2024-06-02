package com.example.FORMANTO.dto.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaymentResponse {

    private List<Long> scarcityProductIds;
    private List<Integer> scarcityProductCounts;
    private List<String> scarcityProductNames;

    @Builder
    public PaymentResponse(List<Long> scarcityProductIds, List<Integer> scarcityProductCounts, List<String> scarcityProductNames) {
        this.scarcityProductIds = scarcityProductIds;
        this.scarcityProductCounts = scarcityProductCounts;
        this.scarcityProductNames = scarcityProductNames;
    }
}
