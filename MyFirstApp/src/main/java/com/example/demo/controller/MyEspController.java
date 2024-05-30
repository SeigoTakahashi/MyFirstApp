package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Esptran;
import com.example.demo.repository.EsptranRepository;

@Controller
public class MyEspController {

	@Autowired
	//↓P16を参考に。
	EsptranRepository esptranRepository;

	@RequestMapping(path = "/myesp", method = RequestMethod.GET)
	public String viewPage(Model model) {

		/**
		 * データベースに全件検索をかける。(P16を参考に！）
		 * 検索結果をmodelに格納し、htmlで表示できるようにしよう。
		 * （冊子P11を参考に！ & メソッドの引数にmodelを定義する事。）
		 */

		List<Esptran> resultList = esptranRepository.findAll();
		model.addAttribute("resultList", resultList);
		return "myesp";
	}

	//登録＆更新用メソッド
	@RequestMapping(path = "/myespInsUpd", method = RequestMethod.POST)
	public String postInsUpd(String jpn, String esp) {

		/**
		 * DBに登録、更新するためのメソッド。
		 * P16を参考にプログラムを書いてみよう。
		 * 冊子P6,7を参考に画面からの値を受け取る事。
		 * (メソッドの引数忘れ注意)
		 */

		//Esptranクラスのインスタンスを生成する。
		Esptran esptran = new Esptran();
		//パラメータをセットする。(setterメソッドを使ってね)
		esptran.setJpn(jpn);
		esptran.setEsp(esp);
		//リポジトリ変数(esptranRepository)のsaveメソッドを使ってDB操作
		esptranRepository.save(esptran);
		return "redirect:/myesp";
	}

	//追加問題用
	@RequestMapping(path = "/myespTuika", method = RequestMethod.POST)
	public String postTuika(String jpn, Model model) {

		/*
		 * 【追加問題】
		 * 　画面の日本語入力欄から入力された値でjpnに対して、
		 * 　あいまい検索を出来るようにしてね。
		 * 　冊子のP17あたりを参考に！
		 */
		List<Esptran> resultList = esptranRepository.findByJpnLike("%" + jpn + "%");
		model.addAttribute("resultList",resultList);

		return "myesp";
	}
}