package com.authentication.authentication.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDTO {
    private String username;
    private String email;
    private String password;
}
