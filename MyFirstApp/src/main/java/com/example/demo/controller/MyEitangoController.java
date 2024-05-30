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
public class MyEitangoController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//一覧表示用
	@RequestMapping(path = "/myeitango", method = RequestMethod.GET)
	public String viewPage(Model model) {

		/**
		 * DBに登録されているデータを取得する。
		 * 取得後、modelに格納し、htmlで表示できるようにする。
		 * 冊子8ページ、9ページ(modelの利用～modelに格納)
		 * 冊子11ページ(htmlで表示できるようにする）
		 * を参考に！
		 */
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM eitango");

		model.addAttribute("list",resultList);

		return "myeitango";
	}

	//INSERT（登録）用メソッド
	@RequestMapping(path = "/myeitangoIns", method = RequestMethod.POST)
	public String postIns(String eng, String jpn) {

		/**
		 * 画面から入力された値を使ってDBに登録する。
		 * 冊子6ページ、7ページ(画面からの値受け取り)
		 * 冊子11ページ、12ページ（DB操作）
		 * を参考に！
		 * ちなみに、INSERT文は
		 * INSERT INTO テーブル名 (列名A,列名B) VALUES(列名Aの値,列名Bの値);
		 * 例：INSERT INTO hoge (colA,colB) VAUES(100,200);
		 *     (hogeテーブルに、列「colA」が「100」、「colB」が「200」のデータを登録。
		 */
		jdbcTemplate.update("INSERT INTO eitango VALUES(?,?)", eng, jpn);
		return "redirect:/myeitango";
	}

	//UPDATE(更新)用メソッド
	@RequestMapping(path = "/myeitangoUpd", method = RequestMethod.POST)
	public String postUpd(String eng, String jpn) {

		/**
		 * 画面から入力された値を使ってDBに登録する。
		 * 冊子6ページ、7ページ(画面からの値受け取り)
		 * 冊子11ページ、12ページ（DB操作）
		 * を参考に！
		 * ちなみに、UPDATE文は、
		 * UPDATE テーブル名 SET 列名 = 変更後の値 WHERE 条件(どのデータか);
		 * 例：UPDATE hoge SET colA = 100 WHERE id = 1;
		 *     (hogeテーブルにあるidが1のデータの、colAを100に変更する)
		 */
		jdbcTemplate.update("UPDATE eitango SET jpn = ? WHERE eng = ?", jpn, eng);

		return "redirect:/myeitango";
	}

	//追加問題用
	@RequestMapping(path = "/myeitangoSr", method = RequestMethod.POST)
	public String postSr(Model model ,String jpn) {

		/**
		 * 冊子のModel,form,DB操作を参考にしてみよう。
		 */
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM eitango WHERE jpn = ?",jpn);
		model.addAttribute("list",resultList);
		return "myeitango";
	}
}