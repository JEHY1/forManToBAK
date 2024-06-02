package com.example.FORMANTO.dto.order;

import com.example.FORMANTO.domain.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressInfoResponse {
    private String postalCode;
    private String roadNameAddress;
    private String detailAddress;

    public AddressInfoResponse(Customer customer){
        this.postalCode = customer.getPostalCode();
        this.roadNameAddress = customer.getRoadNameAddress();
        this.detailAddress = customer.getDetailAddress();
    }
}
