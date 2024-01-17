package com.study.springboot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.api.request.JoinRequestDto;
import com.study.springboot.api.request.LoginRequestDto;
import com.study.springboot.dto.AddUserRequest;
import com.study.springboot.dto.LoginUserRequest;
import com.study.springboot.service.RestaurantService;
import com.study.springboot.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;


@RestController
//@SecurityRequirement(name = "Bearer Authentication")
public class UserApi {
	@Autowired
	private  UserService userService;
	
	
	 @Operation(
			    summary = "회원가입",
			    description = "회원가입"
			)
	 @CrossOrigin
	@PostMapping("/api/join")
	 public String join(@RequestBody AddUserRequest request) {
		 // 이메일 중복 체크
	        String email = request.getEmail();
	        if (userService.isEmailAlreadyExists(email)) {
	            return "이메일이 중복되었습니다.";
	        }

	        // 중복된 이메일이 없으면 사용자 저장
	        Long userId = userService.save(request);
	        return "User created with ID: " + userId;
    }
	 
	 @CrossOrigin
	 @PostMapping("/api/login")
	    public ResponseEntity<?> login(@RequestBody LoginUserRequest request) {
	        return userService.login(request);
	    }
}
