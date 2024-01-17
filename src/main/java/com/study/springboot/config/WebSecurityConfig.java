package com.study.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import com.study.springboot.security.JwtAuthenticationFilter;


import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {
//	private final UserRepository userRepository;
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter; 

	
	@Bean
	public WebSecurityCustomizer configure() {
		return (web) -> web.ignoring()
//				.requestMatchers(toH2Console())
				.requestMatchers("/static/**"); 
	}
	
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
        		.cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable() // http의 기본 인증. ID, PW 인증방식
                .authorizeRequests()
                .requestMatchers("/status", "/v2/api-docs", "/swagger-ui/**", "/images/**", "/api/join", "/api/login","/restaurants").permitAll()
                .requestMatchers("/restaurant/**").authenticated()
//				.anyRequest().authenticated()                
                .anyRequest().permitAll()
//                .and()
//                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
                .and()
                .addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // JwtAuthenticationFilter를 AnotherFilter 뒤에 추가

        return http.build();

  }
	
	

}
