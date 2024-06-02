package com.example.FORMANTO.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_group_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class ProductGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_group_id", updatable = false)
    private Long productGroupId;

    @Column(name = "category_detail_id", nullable = false)
    private Long categoryDetailId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "details", nullable = false)
    private String details;

    @Column(name = "weight", nullable = false)
    private String weight;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "period", nullable = false)
    private String period;

    @Column(name = "product_manual", nullable = false)
    private String manual;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "precautions", nullable = false)
    private String precautions;

    @Column(name = "assurance", nullable = false)
    private String assurance;

    @Column(name = "counselor_phone_number", nullable = false)
    private String counselorPhoneNumber;

    @Column(name = "sale_count", nullable = false)
    private int saleCount;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "group_price", nullable = false)
    private int groupPrice;

    @Column(name = "rep_img_src", nullable = false)
    private String repImgSrc;

    @Builder
    public ProductGroup(Long categoryDetailId, String name, String details, String weight, String summary, String period, String manual, String manufacturer, String country, String precautions, String assurance, String counselorPhoneNumber, int saleCount, String company, int groupPrice, String repImgSrc) {
        this.categoryDetailId = categoryDetailId;
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
        this.counselorPhoneNumber = counselorPhoneNumber;
        this.saleCount = saleCount;
        this.company = company;
        this.groupPrice = groupPrice;
        this.repImgSrc = repImgSrc;
    }

    public void updateSaleCount(int totalCount){
        this.saleCount += totalCount;
    }
}
