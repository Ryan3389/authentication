package com.authentication.authentication.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String token;
    private long expiresIn;
    private String status;

    public UserResponse(String token, long expiresIn, String status) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.status = status;
    }
}
