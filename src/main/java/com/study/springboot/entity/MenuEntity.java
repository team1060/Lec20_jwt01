package com.study.springboot.entity;

import java.time.ZonedDateTime;

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

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="menu")
@Entity
public class MenuEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menuid_seq")
	@SequenceGenerator(name="menuid_seq",sequenceName = "menuid_SEQ", allocationSize = 1)
	private Long id;
	
	private Long restaurantId;
    private String name;
    private Integer price;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
