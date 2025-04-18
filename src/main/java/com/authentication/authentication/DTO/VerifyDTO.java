package com.authentication.authentication.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyDTO {
    private String email;
    private String verificationCode;
}
