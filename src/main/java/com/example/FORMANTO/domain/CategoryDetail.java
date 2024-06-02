package com.example.FORMANTO.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Getter
@Table(name = "category_detail_tb")
public class CategoryDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "category_detail_id", updatable = false)
    private Long categoryDetailId;

    @Column (name = "category_id")
    private Long categoryId;

    @Column (name = "Field")
    private String Field;



//    @ManyToOne(optional = false)
//    private ProductGroup productGroup;

    @Builder
    public CategoryDetail(Long categoryId, String Field) {

        this.categoryId = categoryId;
        this.Field = Field;
    }


}
