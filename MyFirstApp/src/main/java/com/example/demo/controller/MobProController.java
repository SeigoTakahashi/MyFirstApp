package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MobProController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//一覧表示用
	@RequestMapping(path = "/mobView", method = RequestMethod.GET)
	public String viewPage(Model model) {
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM mobpro");

		model.addAttribute("list",resultList);


		return "mobpro";
	}

	//INSERT（登録）用メソッド
	@RequestMapping(path = "/mobIns", method = RequestMethod.POST)
	public String postIns(String role,String name,String goodpoint) {
		
		jdbcTemplate.update("INSERT INTO mobpro VALUES(?,?,?)", role, name,goodpoint);
		return "redirect:/mobView";
	}

	//UPDATE(更新)用メソッド
	@RequestMapping(path = "/mobUpd", method = RequestMethod.POST)
	public String postUpd(String role,String name,String goodpoint) {
		jdbcTemplate.update("UPDATE mobpro SET role = ?,goodpoint = ? WHERE name = ?", role, goodpoint,name);
		return "redirect:/mobView";
	}

	//???用メソッド
	@RequestMapping(path = "/mobSr", method = RequestMethod.POST)
	public String postSr() {

		return "mobpro";
	}
}