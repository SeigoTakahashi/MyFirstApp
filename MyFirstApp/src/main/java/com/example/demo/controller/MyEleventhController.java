package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyEleventhController {
	//DBへつなぐために必要
	@Autowired
	JdbcTemplate jdbcTemplate;

	//URLが「/myeleventh」の時に「index.html」を表示させるように
	//プログラムを書いてみてね。
	@RequestMapping(path = "/myeleventh", method = RequestMethod.GET)
	public String oneGet() {
		return "index";
	}

	//画面からformタグで送られた値を処理するメソッド
	@RequestMapping(path = "/myeleventh", method = RequestMethod.POST)
	public String onePost(String name,String email,String subject,String message) {
		//受け取った値をSysoutで表示させる
		//????に入る変数を考えよう(書く時は???のダブルクォーテーション外してね。)
		System.out.println("名前は" + name);
		System.out.println("メールは" + email);
		System.out.println("件名は" + subject);
		System.out.println("メッセージは" + message);
		//DBに追加するならここにSQLを書く。
		return "index";
	}
}