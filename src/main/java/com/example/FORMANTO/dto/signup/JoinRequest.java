package com.example.FORMANTO.dto.signup;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JoinRequest {

    private String username;
    private String password;
    private String phoneNum;
    private String email;
    private String adminKey;

}
