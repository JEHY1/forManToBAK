package com.example.FORMANTO.controller;

import com.example.FORMANTO.domain.Product;
import com.example.FORMANTO.domain.ProductGroup;
import com.example.FORMANTO.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductViewController {

    private final ReviewService reviewService;
    private final QnAService qnAService;
    private final ProductService productService;
    private final WishListService wishListService;
    private final ImgService imgService;

    @GetMapping("/productDetail/{id}")
    public String productDetail(@PathVariable long id, @RequestParam(required = false) List<Long> PGL, Model model, Principal principal){

        ProductGroup productGroup = productService.findProductGroupByProductGroupId(id);
        //path로 전달 받은 id로 product_group_tb 에서 검색
        if(productGroup != null) { //존재하는지 확인 후
            model.addAttribute("reviews", reviewService.productReviews(id));
            model.addAttribute("QnAs", qnAService.productQnA(id));
            model.addAttribute("products", productService.findProductByProductGroupId(id));
            model.addAttribute("productGroup", productGroup); //각 속성에 맞게 넘겨줌
            model.addAttribute("productInfoImgs", imgService.getInfoImgSrc(id));
            model.addAttribute("PGL", PGL);

            if(principal != null){
                model.addAttribute("principal", principal);
                model.addAttribute("isWish", wishListService.isWish(principal, id));
                model.addAttribute("userName", principal.getName());
            }
        }

        return "product/productDetail";
    }

    @GetMapping("/main")
    public String home(){
        return "mainPage";
    }
}
