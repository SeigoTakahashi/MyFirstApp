package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Hannou;

public interface HannouRepository extends JpaRepository<Hannou, Integer> {

}