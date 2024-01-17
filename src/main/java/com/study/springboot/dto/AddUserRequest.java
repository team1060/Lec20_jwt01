package com.study.springboot.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddUserRequest {
	private String email;
    private String password;
    private String username;
    private String role;
    private String authProvider;
}
