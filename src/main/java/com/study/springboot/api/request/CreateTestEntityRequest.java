package com.study.springboot.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateTestEntityRequest {
	private final String name;
    private final Integer age;
}
