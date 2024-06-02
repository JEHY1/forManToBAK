package com.example.FORMANTO.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.xml.stream.FactoryConfigurationError;
import java.time.LocalDateTime;

@Entity
@Table(name = "QnA_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
@ToString
public class QnA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qna_id", updatable = false)
    private Long qnaId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "product_group_id", nullable = false)
    private Long productGroupId;

    @CreatedDate
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "answer", nullable = false)
    private String answer;

    @Builder
    public QnA(Long customerId, Long productGroupId, LocalDateTime date, String status, String question, String answer) {
        this.customerId = customerId;
        this.productGroupId = productGroupId;
        this.date = date;
        this.status = status;
        this.question = question;
        this.answer = answer;
    }
}
