package com.example.FORMANTO.controller.api;

import com.example.FORMANTO.domain.QnA;
import com.example.FORMANTO.dto.productDetail.QnAQuestionSubmitRequest;
import com.example.FORMANTO.service.QnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class QnAApiController {

    private final QnAService qnAService;

    @PostMapping("/api/QnAQuestion")
    public ResponseEntity<QnA> question(@RequestBody QnAQuestionSubmitRequest request, Principal principal){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(qnAService.save(request, principal));
    }
}
