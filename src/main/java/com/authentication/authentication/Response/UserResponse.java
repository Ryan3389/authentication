package com.authentication.authentication.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String token;
    private long expiresIn;

    public UserResponse(String token, long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }
}
