package com.example.FORMANTO.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private static final char[] chars;
    private static final String senderEmail = "poiuy015201@gmail.com";

    private final JavaMailSender javaMailSender;

    static{ //chars{a-z A-Z 0-9}
        StringBuilder sb =new StringBuilder();
        for(char c = '0'; c <= '9'; ++c){
            sb.append(c);
        }
        for(char c = 'A'; c <= 'Z'; ++c){
            sb.append(c);
        }
        chars = sb.toString().toCharArray();
    }

    private String makeAuthKey(){ //email 인증용 키 생성 알고리즘
        StringBuilder key = new StringBuilder();
        for(int i = 0; i < 6; i++){
            int index = (int)(Math.random() * 36);
            key.append(chars[index]);
        }
        return key.toString();
    }

    public MimeMessage createAuthMail(String mail, String key){
        MimeMessage message = javaMailSender.createMimeMessage(); //메시지 객체 생성
        try{
            message.setFrom(senderEmail); //송신자 email 설정
            message.setRecipients(MimeMessage.RecipientType.TO, mail);//수신자 email 설정
            message.setSubject("mail send test"); //제목 설정
            //내용 설정
            String body = "<h1>html type mail</h1>\n<p>authentication key : " + key + "</p>";
            message.setContent(body, "text/html");
        }catch (MessagingException e){
        }

        return message;
    }

    public String sendMail(String mail){
        String key = makeAuthKey(); // email 인증용 키 생성
        MimeMessage message = createAuthMail(mail, key); //메일 생성
        //메일 전송
        try{
            javaMailSender.send(message);
        }catch(Exception e){
        }

        return key;
    }


}
