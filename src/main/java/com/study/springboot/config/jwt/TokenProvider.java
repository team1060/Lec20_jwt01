package com.study.springboot.config.jwt;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.study.springboot.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenProvider {
	private final JwtProperties jwtProperties;
	public String generateToken(User user, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

	//토큰 생성 메소스
    private String makeToken(Date expiry, User user) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setSubject(user.getEmail())
                .claim("email", user.getEmail())
                .claim("name", user.getUsername())
                .claim("role", user.getRole())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }
    //토큰 유호성 검사
//    public String validateAndGetUserId(String token) {
//        try {
//            Jwts.parser()
//                    .setSigningKey(jwtProperties.getSecretKey()) //비밀값으로 복호
//                    .parseClaimsJws(token);
//
//            return true;
//        } catch (Exception e) { //복호화 과정에서 에러 나면 유효하지 않는 토큰
//            return false;
//        }
    	
//    	Claims claims = Jwts.parser()
//              .setSigningKey(jwtProperties.getSecretKey()) //비밀값으로 복호
//              .parseClaimsJws(token)
//              .getBody();
//    	return claims.getSubject();
//    }
    //토큰 유효성검사
    public String validateAndGetUserId(String token) throws JwtException {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey()) // 비밀값으로 복호
                    .parseClaimsJws(token)
                    .getBody();
            
            return claims.getSubject();
        } catch (JwtException e) {
            // 토큰이 유효하지 않은 경우 예외 처리
            throw new JwtException("토큰이 맞지 않아요");
        }
    }

//    //토큰 기반으로 인증 정보를 가져오는 메소드
//    public Authentication getAuthentication(String token) {
//        Claims claims = getClaims(token);
//        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
//
//        return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(claims.getSubject
//                (), "", authorities), token, authorities);
//    }
//
//    //토큰 기반으로 유저 id를 가져오는 메소드
//    public Long getUserId(String token) {
//        Claims claims = getClaims(token);
//        return claims.get("id", Long.class);
//    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}
