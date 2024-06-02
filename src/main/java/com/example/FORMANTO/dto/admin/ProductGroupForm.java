package com.example.FORMANTO.dto.admin;


import lombok.*;


@Getter @Setter @ToString @Data
public class ProductGroupForm {



    private Long product_group_id;


    private Long category_detail_id;
    private String name;
    private String details;
    private String weight;
    private String summary;
    private String period;
    private String manual;
    private String manufacturer;
    private String country;
    private String precautions;
    private String assurance;
    private String counselor_phone_number;
    private String company;
    private int price_group;

    private int sale_count;

    private String rep_img_src;

    @Builder
    public ProductGroupForm (Long product_group_id, Long category_detail_id,
                             String name, String details, String weight, String summary,
                             String period, String manual, String manufacturer,
                             String country, String precautions, String assurance,
                             String counselor_phone_number,
                             String company, int price_group, int sale_count, String rep_img_src) {

        this.product_group_id = product_group_id;
        this.category_detail_id = category_detail_id;
        this.name = name;
        this.details = details;
        this.weight = weight;
        this.summary = summary;
        this.period = period;
        this.manual = manual;
        this.manufacturer = manufacturer;
        this.country = country;
        this.precautions = precautions;
        this.assurance = assurance;
        this.counselor_phone_number = counselor_phone_number;
        this.company = company;
        this.price_group = price_group;

        this.sale_count = sale_count;

        this.rep_img_src = rep_img_src;
    }

}
