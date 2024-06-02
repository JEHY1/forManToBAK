package com.example.FORMANTO.service;


import com.example.FORMANTO.domain.QnA;
import com.example.FORMANTO.dto.QnAMypageResponse;
import com.example.FORMANTO.repository.QnARepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class QnAMypageService {

    private final QnARepository qnaRepository;

    public List<QnA> findByCustomerId(Long customerId) {
        return qnaRepository.findByCustomerId(customerId)
                .orElseThrow(()->new IllegalArgumentException("not found qna"));
    }

    //qna db에서 데이터 view로 옮기기
    public List<QnAMypageResponse> myPageQnaView(Long customer) {

        List<QnAMypageResponse> qnAMypageResponses = new ArrayList<>();
        List<QnA> qnas = findByCustomerId(customer);

        qnas.forEach(qnA -> {

            qnAMypageResponses.add(QnAMypageResponse.builder()
                    .qnaId(qnA.getQnaId())
                    .date(qnA.getDate())
                    .status(qnA.getStatus())
                    .product("")
                    .question(qnA.getQuestion())
                    .answer(qnA.getAnswer())
                    .build());
        });

        return qnAMypageResponses;
    }
}