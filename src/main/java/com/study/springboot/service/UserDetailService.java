package com.study.springboot.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.study.springboot.entity.User;
import com.study.springboot.repository.UserRepository;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
	private final UserRepository userRepository;

	@Override
	public User loadUserByUsername(String email){
		return userRepository.findByEmail(email);
	
	}
}
