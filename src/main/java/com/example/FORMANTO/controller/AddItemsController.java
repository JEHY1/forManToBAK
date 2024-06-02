package com.example.FORMANTO.controller;


import com.example.FORMANTO.domain.Img;
import com.example.FORMANTO.domain.Product;
import com.example.FORMANTO.domain.ProductGroup;
import com.example.FORMANTO.dto.admin.ImgForm;
import com.example.FORMANTO.dto.admin.ProductForm;
import com.example.FORMANTO.dto.admin.ProductGroupForm;
import com.example.FORMANTO.repository.ImgRepository;
import com.example.FORMANTO.repository.ProductGroupRepository;
import com.example.FORMANTO.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller

public class AddItemsController {

    @Autowired
    private ProductGroupRepository productGroupRepository;
    @Autowired
    private ImgRepository imgRepository;
    @Autowired
    private ProductRepository productRepository;


//    private String AdditemsRepository = "redirect:/admin/adminItemList";


    @GetMapping("admin/adminAdditems")
    public String newProductGroupForm() {

        return "/admin/adminAdditems";
    }


    @PostMapping("/adminAdditems/create")
    public String createProductGroup(ProductGroupForm form, ImgForm form2,
                                     ProductForm form3) {
//        @RequestParam("src") MultipartFile[] imges,
//        @RequestParam("imgSrc") MultipartFile[] imgSrcs


        System.err.println("post");

        log.info(form.toString() + form2.toString() + form3.toString());

        ProductGroup productGroup = ProductGroup.builder()
//                .productGroupId(form.getProduct_group_id())
                .categoryDetailId(form.getCategory_detail_id())
                .name(form.getName())
                .details(form.getDetails())
                .weight(form.getWeight())
                .summary(form.getSummary())
                .period(form.getPeriod())
                .manual(form.getManual())
                .manufacturer(form.getManufacturer())
                .country(form.getCountry())
                .precautions(form.getPrecautions())
                .assurance(form.getAssurance())
                .counselorPhoneNumber(form.getCounselor_phone_number())
                .company(form.getCompany())
                .groupPrice(form.getPrice_group())

                .saleCount(form.getSale_count())

                .repImgSrc(form.getRep_img_src())

                .build();

        productGroup = productGroupRepository.save(productGroup);

        System.err.println("prdouctGroup :" + productGroup);


        Img img = Img.builder()
                .productGroupId(productGroup.getProductGroupId())
                .src(form2.getSrc())
                .build();

        imgRepository.save(img);


        Product product = Product.builder()
                .productGroupId(productGroup.getProductGroupId())
                .price(form3.getPrice())
                .quantity(form3.getQuantity())
                .name(form3.getNameP())
                .imgSrc(form3.getImg_src())
                .build();

        productRepository.save(product);

        return "redirect:/admin/adminAdditems";
    }


    //dto 엔티티 변환

//        AdditemsImg additemsImg = form2.toEntity();
//        ItemOption itemOption = form3.toEntity();
//        log.info(additems.toString());
//
//        additemsImgRepository.save(additemsImg);
//        itemOptionRepository.save(itemOption);
//


//    @Autowired
//    private AdditemsImgRepository additemsImgRepository;
//    @PostMapping("adminAdditems/create1")
//    public String newAdditemsImgFrom(AdditemsImgForm form) {
//        log.info(form.toString());
//
//        AdditemsImg additemsImg = form.toEntity();
//        log.info(additemsImg.toString());
//
//        AdditemsImg saved = additemsImgRepository.save(additemsImg);
//        log.info(saved.toString());
//
//        return "";
//    }
//
//    @Autowired
//    private ItemOptionRepository itemOptionRepository;
//    @PostMapping("adminAdditems/create2")
//    public String newItemOptionForm(ItemOptionForm form) {
//        log.info(form.toString());
//        ItemOption itemOption = form.toEntity();
//        log.info(itemOption.toString());
//        ItemOption saved = itemOptionRepository.save(itemOption);
//        log.info(saved.toString());
//        return "redirect:/adminAdditems/";
//    }


//    @GetMapping("/admin/{product_group_id}")
//    public String show(@PathVariable Long product_group_id, Model model){
//        log.info("product_group_id = " + product_group_id);
//        Optional<Additems> additemsEntity = additemsRepository.findById(product_group_id);
//        model.addAttribute("Additems", additemsEntity);
//        return "admin/adminItemList";
//    }


    //    상품리스트
    @GetMapping("/admin")
    public String itemList(Model model) {
        List<ProductGroup> productGroupsEntity = productGroupRepository.findAll();
        model.addAttribute("additemlist", productGroupsEntity);
        return "/admin/adminMenu";

    }


}