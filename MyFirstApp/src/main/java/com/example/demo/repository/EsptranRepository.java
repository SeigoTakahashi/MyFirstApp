package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Esptran;

//コメントを外して挑戦してね。
public interface EsptranRepository extends JpaRepository<Esptran, String> {

//追加問題用
	List<Esptran> findByJpnLike(String jpn);

}