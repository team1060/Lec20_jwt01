package com.study.springboot.api.request;

import lombok.Data;

@Data
public class JoinRequestDto {
	private String email;
    private String username;
    private String password;
}
