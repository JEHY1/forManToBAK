package com.example.FORMANTO.dto.signup;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthKeyResponse {

    private String authKey;
}
