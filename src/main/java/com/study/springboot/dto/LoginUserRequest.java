package com.study.springboot.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginUserRequest {
	private String email;
    private String password;

}
