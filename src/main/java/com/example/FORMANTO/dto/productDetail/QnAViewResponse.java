package com.example.FORMANTO.dto.productDetail;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QnAViewResponse {

    private Long qnAId;
    private String question;
    private String username;
    private LocalDateTime date;
    private String answer;
    private String status;

    @Builder
    public QnAViewResponse(Long qnAId, String question, String username, LocalDateTime date, String answer, String status) {
        this.qnAId = qnAId;
        this.question = question;
        this.username = username;
        this.date = date;
        this.answer = answer;
        this.status = status;
    }
}
