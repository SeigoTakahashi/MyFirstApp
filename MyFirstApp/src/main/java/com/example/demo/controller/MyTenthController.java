package com.example.demo.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;

@Controller
public class MyTenthController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	UsersRepository usersRepository;

	@RequestMapping(path = "/mytenth", method = RequestMethod.GET)
	public String tenthGet(Model model) {

		
		List<Users> userslist = usersRepository.findAll();

		model.addAttribute("selectResult", userslist);

		return "mytenth";
	}

	@RequestMapping(path = "/mytenth", method = RequestMethod.POST)
	public String tenthPostUpdate(String user_id, String user_pass, String user_name, MultipartFile upimage,
			Model model) throws IOException {

		//アップロードされたメディアデータ(upimage)をバイトデータに変換する。
		byte[] byteData = upimage.getBytes();

		//バイトデータをBase64に変換する。
		//（変数「encodedImage」にエンコードされた画像が格納されてる）
		String encodedImage = Base64.getEncoder().encodeToString(byteData);
		String yokumite = user_name;

		//データ更新SQL実行
		jdbcTemplate.update("UPDATE users SET user_pass=?,user_name=?,user_image=? WHERE user_id = ?;", user_pass,
				yokumite, encodedImage, user_id);

		return "redirect:/mytenth";
	}

	@RequestMapping(path = "/mytenth_delete", method = RequestMethod.POST)
	public String tenthPostDelete(String user_id) throws IOException {

		//データ削除SQL実行
		jdbcTemplate.update("DELETE FROM users WHERE user_id = ?", user_id);

		return "redirect:/mytenth";
	}
}