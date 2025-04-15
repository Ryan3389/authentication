package com.authentication.authentication.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Verify {
    private String email;
    private String verificationCode;
}
