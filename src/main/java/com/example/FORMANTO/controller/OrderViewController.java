package com.example.FORMANTO.controller;

import com.example.FORMANTO.domain.Product;
import com.example.FORMANTO.dto.order.AddressInfoResponse;
import com.example.FORMANTO.dto.order.OrderRequest;
import com.example.FORMANTO.dto.order.OrderViewResponse;
import com.example.FORMANTO.service.CustomerService;
import com.example.FORMANTO.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderViewController {

    private final ProductService productService;
    private final CustomerService customerService;

    @PostMapping("/order") //상세 페이지,장바구니 페이지 -> 구매 페이지
    public String order (@RequestBody OrderRequest request, Model model, Principal principal){ //List<Long> productIds , List<Integer>counts 를 전달받음
        model.addAttribute("ids", request.getProductIds());
        model.addAttribute("counts", request.getCounts());
        List<OrderViewResponse> selectedProducts = new ArrayList<>();

        int index = 0;
        for(Product product : productService.findAllProductByProductIdIn(request.getProductIds())){
            selectedProducts.add(new OrderViewResponse(product, request.getCounts().get(index++)));
        } //각각의 제품 구매페이지를 위한 List<OrderViewResponse> 생성 OrderViewResponse

        model.addAttribute("selectedProducts", selectedProducts);
        model.addAttribute("totalPrice", productService.priceCalc(request.getProductIds(), request.getCounts()));
        model.addAttribute("cartIds", request.getCartIds());

        if(principal != null){
            model.addAttribute("addressInfo", new AddressInfoResponse(customerService.findByUsername(principal.getName())));
        }
        else{
            model.addAttribute("addressInfo", new AddressInfoResponse());
        }





        return "/user/order";
    }

    @GetMapping("/order")
    public String order(){
        return "user/order";
    }
}
