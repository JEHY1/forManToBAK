package com.example.FORMANTO.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
//05 23
import java.time.LocalDateTime;

@Data
public class QnAMypageResponse {
    //    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Long qnaId;
    private LocalDateTime date; //주문일자
    private String status; //답변상태
    private String product;//주문상품
    private String question; //문의내용
    private String answer;// 답변


    @Builder
    public QnAMypageResponse(LocalDateTime date, String status, String product, String question, String answer, Long qnaId) {
        this.date = date;
        this.status = status;
        this.product = product;
        this.question = question;
        this.answer = answer;
        this.qnaId = qnaId;
    }
}