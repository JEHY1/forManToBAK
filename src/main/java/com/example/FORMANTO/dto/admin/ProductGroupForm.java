package com.example.FORMANTO.dto;


import lombok.*;


@Data
@Getter
//@AllArgsConstructor
@ToString
public class ProductGroupForm {

    private Long product_group_id;
    private Long category_detail_id;
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
    private int sale_count;
    private String company;
    private String name;
    private int price;



    @Builder
    public ProductGroupForm(Long product_group_id, Long category_detail_id, String details, String weight, String summary,
                            String period, String manual, String manufacturer, String country, String precautions, String assurance,
                            String counselor_phone_number, int sale_count, String company, String name, int price) {

        this.product_group_id = product_group_id;
        this.category_detail_id = category_detail_id;
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
        this.sale_count = sale_count;
        this.company = company;
        this.name = name;
        this.price = price;
    }

/*
    public Additems toEntity() {
        return new Additems(category_detail_id, details, weight, summary,
                period, manual, manufacturer, country, precautions, assurance,
                counselor_phone_number, sale_count, company, name, price);
    }
*/


}