package com.example.FORMANTO.domain;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Getter
@Table(name = "category_tb")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", updatable = false)
    private Long categoryId;

    @Column(name = "name", nullable = false)
    private String name;


    @Builder
    private Category (String name) {
        this.name = name;
    }

}

