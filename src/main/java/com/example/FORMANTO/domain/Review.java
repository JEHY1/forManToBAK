package com.example.FORMANTO.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "review_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", updatable = false)
    private Long reviewId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "product_group_id", nullable = false)
    private Long productGroupId;

    @CreatedDate
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Review(String username, Long productGroupId, LocalDateTime date, String content) {
        this.username = username;
        this.productGroupId = productGroupId;
        this.date = date;
        this.content = content;
    }
}
