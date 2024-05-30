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
public class MyInjectionController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//ログイン画面表示用
	@RequestMapping(path = "/sqlinjection", method = RequestMethod.GET)
	public String viewPage() {

		return "myinjection";
	}

	//ログイン処理(送信ボタンを押した際に動く)
	@RequestMapping(path = "/sqlinjection", method = RequestMethod.POST)
	public String viewPost(String user_id, Model model) {

		List<Map<String, Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM privacy WHERE id = ?", user_id);

		model.addAttribute("resutList", resultList);

		return "myinjection";
	}
}