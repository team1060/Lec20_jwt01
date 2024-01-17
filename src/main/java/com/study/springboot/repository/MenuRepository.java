package com.study.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.springboot.entity.MenuEntity;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    public List<MenuEntity> findAllByRestaurantId(Long restaurantId);
}
