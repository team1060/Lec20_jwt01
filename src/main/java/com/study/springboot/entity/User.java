package com.study.springboot.entity;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="users")
public class User implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
	@SequenceGenerator(name="users_seq",sequenceName = "users_SEQ", allocationSize = 1)
	private Long Id;
	private String email;
	private String password;
	
	private String username;
	private String role; // 유저의 롤.
	private String authProvider; // example : facebook
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return email;
    }
    

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
