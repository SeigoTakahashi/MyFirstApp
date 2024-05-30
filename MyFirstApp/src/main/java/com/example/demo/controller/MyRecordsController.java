package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Records;
import com.example.demo.repository.RecordsRepository;

@Controller
public class MyRecordsController {

	@Autowired
	RecordsRepository recordsRepository;

	@RequestMapping(path = "/myrecords", method = RequestMethod.GET)
	public String recoSele(Model model) {

		//全件検索(findAll)した結果を変数「userlist」にしまっている。
		//ヒント①：今回は何という名前のエンティティを使っているかな？
		//ヒント②：Repository19行目当たりにあるrepositoryの変数名は？
		List<Records> recordslist = recordsRepository.findAll();

		model.addAttribute("recordslist", recordslist);

		return "myrecords";
	}

	//登録＆更新用メソッド
	@RequestMapping(path = "/myrecordsInsUpd", method = RequestMethod.POST)
	public String recoInsUpd(Model model, String record_id, String record_name, String game_count, String win) {

		int uid = Integer.parseInt(record_id);
		int gcount = Integer.parseInt(game_count);
		int iwin = Integer.parseInt(win);

		Records myRecord = new Records();

		myRecord.setUser_id(uid);
		myRecord.setUser_name(record_name);
		myRecord.setGame_count(gcount);
		myRecord.setWin(iwin);

		//Repositoryを介してDBに登録
		recordsRepository.save(myRecord);

		return "redirect:/myrecords";
	}

	//削除用メソッド
	@RequestMapping(path = "/myrecordsDelete", method = RequestMethod.POST)
	public String recoDel(String record_id) {

		int uid = Integer.parseInt(record_id);

		recordsRepository.deleteById(uid);

		return "redirect:/myrecords";
	}
}