package com.study.springboot.service;


import java.time.Duration;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.springboot.api.response.LoginResponse;
import com.study.springboot.config.jwt.TokenProvider;
import com.study.springboot.dto.AddUserRequest;
import com.study.springboot.dto.LoginUserRequest;
import com.study.springboot.entity.User;
import com.study.springboot.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	 private final TokenProvider tokenProvider;

	 
	 public boolean isEmailAlreadyExists(String email) {
	        return userRepository.existsByEmail(email);
	    }
	 
	 
	 public Long save(AddUserRequest dto) {
		    // 중복된 이메일이 없으면 사용자 저장
		    User user = User.builder()
		            .email(dto.getEmail())
		            .password(bCryptPasswordEncoder.encode(dto.getPassword()))
		            .username(dto.getUsername())
		            .role(dto.getRole())
		            .authProvider(dto.getAuthProvider())
		            .createdAt(ZonedDateTime.now())
	                .updatedAt(ZonedDateTime.now())
		            .build();

		    return userRepository.save(user).getId();
		}
	
	
	public ResponseEntity<LoginResponse> login(LoginUserRequest request) {
        // 사용자 이름과 비밀번호를 기반으로 사용자를 조회
        User user = userRepository.findByEmail(request.getEmail());

     // 사용자가 존재하고 비밀번호가 일치하면 로그인 성공
        if (user != null && bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
            // 토큰 만료 시간 (Duration)을 설정하고 토큰을 생성
            Duration expirationTime = Duration.ofMinutes(30); // 예: 30분
            String token = tokenProvider.generateToken(user, expirationTime);

            // 로그인 성공 및 JWT 토큰 반환
            
            LoginResponse response = new LoginResponse("Login successful!", token, user.getEmail());
            return ResponseEntity.ok(response);
            
            
        } else {
        	// 로그인 실패 응답
            LoginResponse response = new LoginResponse("Login failed. Please check your credentials.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
	

	public User findById(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
	}
}
