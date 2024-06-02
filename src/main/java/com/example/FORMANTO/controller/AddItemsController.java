package com.example.FORMANTO.controller;

import com.example.FORMANTO.domain.Img;
import com.example.FORMANTO.domain.Product;
import com.example.FORMANTO.domain.ProductGroup;
import com.example.FORMANTO.dto.ProductGroupForm;
import com.example.FORMANTO.dto.ImgForm;
import com.example.FORMANTO.dto.ItemOptionForm;
import com.example.FORMANTO.repository.ImgRepository;
import com.example.FORMANTO.repository.ProductGroupRepository;
import com.example.FORMANTO.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Slf4j
//@RequiredArgsConstructor
@Controller

public class AddItemsController {

    @Autowired
    private ProductGroupRepository productGroupRepository;
    @Autowired
    private ImgRepository imgRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/admin/adminAdditems")
    public String newProductGroupForm() {
        return  "admin/adminAdditems";
    }

    @PostMapping("/admin/adminAdditems/create")
    public String createProductGroup (ProductGroupForm form, ImgForm form2, ItemOptionForm form3 ) {

        log.info(form.toString() + form2.toString() + form3.toString());

        ProductGroup productGroup = ProductGroup.builder()
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
                .saleCount(form.getSale_count())
                .company(form.getCompany())
                .groupPrice(form.getPrice())
                .build();
        productGroupRepository.save(productGroup);

        Img img = Img.builder()
                .productGroupId(form2.getProduct_group_id())
                .src(form2.getSrc())
                .build();
        imgRepository.save(img);

        Product product = Product.builder()
                .price(form3.getPrice_option())
                .quantity(form3.getQuantity())
                .name(form3.getName_option())
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

    @GetMapping("/admin")
    public String itemList(Model model){
        List<ProductGroup> productGroupsEntity = productGroupRepository.findAll();
        model.addAttribute("additemlist", productGroupsEntity);
        return "admin/adminMenu";

    }
}