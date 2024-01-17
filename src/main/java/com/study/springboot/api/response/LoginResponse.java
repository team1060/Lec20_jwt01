package com.study.springboot.api.response;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    private String message;
    private String token;
    private String email;

    // Constructors, getters, and setters

    public LoginResponse(String message, String token, String email) {
        this.message = message;
        this.token = token;
        this.email = email;
    }

    public LoginResponse(String message) {
        this.message = message;
    }

    // Getters and setters
}
