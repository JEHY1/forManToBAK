package com.example.FORMANTO.controller.api;

import com.example.FORMANTO.dto.signup.AuthKeyResponse;
import com.example.FORMANTO.dto.signup.sendAuthMailRequest;
import com.example.FORMANTO.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailApiController {

    private final MailService mailService;

    @PostMapping("/authMail") //메일 전송을 위한 api 인증용 키를 리턴
    public ResponseEntity<AuthKeyResponse> sendAuthMail(@RequestBody sendAuthMailRequest request){

        return ResponseEntity.ok()
                .body(new AuthKeyResponse(mailService.sendMail(request.getEmail())));
    }

}
