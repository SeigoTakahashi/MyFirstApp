package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Drinks;

public interface DrinksRepository extends JpaRepository<Drinks, Integer> {

}