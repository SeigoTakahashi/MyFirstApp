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
public class ItemdbController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/items", method = RequestMethod.GET)
	public String itemsGet(Model model) {

		//SELECT文の結果をしまうためのリスト
		List<Map<String, Object>> resultList;

		//SELECT文の実行
		resultList = jdbcTemplate.queryForList("select * from items");

		//実行結果をmodelにしまってHTMLで出せるようにする。
		model.addAttribute("selectResult", resultList);

		return "itemdb";
	}

	//	//商品登録DB
	@RequestMapping(path = "/itemregist", method = RequestMethod.POST)
	public String itemsPostRegist(String item_id, String item_name, String item_price, MultipartFile upimage)
			throws IOException {

		//アップロードされたメディアデータ(upimage)をバイトデータに変換する。
		byte[] byteData = upimage.getBytes();
		//バイトデータをBase64に変換する。
		//（変数「encodedImage」にエンコードされた画像が格納されてる）
		String encodedImage = Base64.getEncoder().encodeToString(byteData);
		//データ登録SQL実行
		jdbcTemplate.update("INSERT INTO items (item_id,item_name,item_price,item_image) VALUES(?,?,?,?);", item_id,
				item_name, item_price, encodedImage);

		return "redirect:/items";
	}

	//商品更新DB
	@RequestMapping(path = "/itemupdate", method = RequestMethod.POST)
	public String itemsPostUpdate(String item_id, String item_name, String item_price, MultipartFile upimage)
			throws IOException {

		//アップロードされたメディアデータ(upimage)をバイトデータに変換する。
		byte[] byteData = upimage.getBytes();
		//バイトデータをBase64に変換する。
		//（変数「encodedImage」にエンコードされた画像が格納されてる）
		String encodedImage = Base64.getEncoder().encodeToString(byteData);
		//データ登録SQL実行
		jdbcTemplate.update("UPDATE items SET item_name=?,item_price=?,item_image=? WHERE item_id = ?;",
				item_name, item_price, encodedImage,item_id);
		return "redirect:/items";
	}

	//商品削除
	@RequestMapping(path = "/itemdelete", method = RequestMethod.POST)
	public String itemsPostDelete(String item_id) throws IOException {

		//データ削除SQL実行
		jdbcTemplate.update("DELETE FROM items WHERE item_id = ?", item_id);
		return "redirect:/items";
	}
}