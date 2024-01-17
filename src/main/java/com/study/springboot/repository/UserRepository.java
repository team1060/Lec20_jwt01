package com.study.springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.study.springboot.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
//	Optional<User> findByEmail(String email);
	User findByEmail(String email);
	boolean existsByEmail(String email);
}
