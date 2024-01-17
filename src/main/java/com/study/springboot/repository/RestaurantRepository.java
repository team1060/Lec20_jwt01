package com.study.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.springboot.entity.RestaurantEntity;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

}
