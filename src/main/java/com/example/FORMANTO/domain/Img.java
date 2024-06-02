package com.example.FORMANTO.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Getter
@Table(name = "img_tb")

public class

Img {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "img_id", updatable = false)
    private Long imgId;

    @Column (name = "product_group_id", nullable = false)
    private Long productGroupId;

    @Column (name = "src", nullable = false)
    private String src;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "")

    @Builder
    public Img(Long productGroupId, String src){

        this.productGroupId = productGroupId;
        this.src = src;
    }
}