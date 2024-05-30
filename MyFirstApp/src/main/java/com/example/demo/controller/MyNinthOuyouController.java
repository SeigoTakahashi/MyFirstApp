package com.example.demo.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MyNinthOuyouController {

	//DBへつなぐために必要
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/myninthouyou", method = RequestMethod.GET)
	public String eidht(Model model) {

		//SELECT文の結果をしまうためのリスト
		List<Map<String, Object>> resultList;

		//SELECT文の実行
		resultList = jdbcTemplate.queryForList("select * from users");

		//実行結果をmodelにしまってHTMLで出せるようにする。
		model.addAttribute("selectResult", resultList);

		return "myninthouyou";
	}

	@RequestMapping(path = "/myninthouyou", method = RequestMethod.POST)
	public String fourthp(String user_id, String user_pass, String user_name, MultipartFile upimage, Model model)
			throws IOException {

		/**
		 * 画像アップロードは「MySixthImageController」で勉強したよ。
		 * 参考にしてみてね。
		 */
		
		//アップロードされたファイルをバイトデータに変換する。
		byte[] byteData = upimage.getBytes();

		//Base64に変換する。
		//（変数「encodedImage」に画像が格納されてる）
		String encodedImage = Base64.getEncoder().encodeToString(byteData);


		//DBに画面から入力されたデータを登録する。
		jdbcTemplate.update("INSERT INTO users (user_id,user_pass,user_name,user_image) VALUES(?,?,?,?);", user_id,
				user_pass, user_name, encodedImage);

		return "redirect:/myninthouyou";
	}
}