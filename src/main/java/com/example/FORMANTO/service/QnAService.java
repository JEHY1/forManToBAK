package com.example.FORMANTO.service;

import com.example.FORMANTO.domain.QnA;
import com.example.FORMANTO.dto.productDetail.QnAQuestionSubmitRequest;
import com.example.FORMANTO.dto.productDetail.QnAViewResponse;
import com.example.FORMANTO.repository.QnARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QnAService {

    private final QnARepository qnARepository;
    private final CustomerService customerService;

    public List<QnA> findByProductGroupId(Long productGroupId){
        return qnARepository.findByProductGroupId(productGroupId)
                .orElseThrow(() -> new IllegalArgumentException("not found QnA"));
    }

    public List<QnAViewResponse> productQnA(Long productGroupId){
        List<QnA> QnAs = findByProductGroupId(productGroupId);
        List<QnAViewResponse> QnAResponse = new ArrayList<>();
        QnAs.forEach(QnA -> {
            QnAResponse.add(QnAViewResponse.builder()
                    .qnAId(QnA.getQnaId())
                    .username("slkdjfsldkjfsd".substring(0,"slkdjfsldkjfsd".length()-4)+"****")
                    .question(QnA.getQuestion())
                    .answer(QnA.getAnswer())
                    .date(QnA.getDate())
                    .status(QnA.getStatus())
                    .build());
        });
        return QnAResponse;
    }

    public QnA save(QnAQuestionSubmitRequest request, Principal principal){

        return qnARepository.save(QnA.builder()
                .productGroupId(request.getProductGroupId())
                .question(request.getQuestion())
                .customerId((principal == null) ? null : customerService.findByUsername(principal.getName()).getCustomerId())
                .status("답변대기")
                .answer("답변을 기다리는 중...")
                .build());
    }
}
