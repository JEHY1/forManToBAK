package com.example.FORMANTO.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "review_img_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReviewImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_img_id", updatable = false)
    private Long reviewImgId;

    @Column(name = "review_id", nullable = false)
    private Long reviewId;

    @Column(name = "review_img_src", nullable = false)
    private String reviewImgSrc;

    @Builder
    public ReviewImg(Long reviewId, String reviewImgSrc) {
        this.reviewId = reviewId;
        this.reviewImgSrc = reviewImgSrc;
    }
}
