package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Items;
import com.example.demo.entity.Users;
import com.example.demo.repository.ItemsRepository;
import com.example.demo.repository.UsersRepository;

@Controller
public class MyTwelfthController {

	//リポジトリ使うよ宣言
	@Autowired
	UsersRepository usersRepository;

	//  練習問題用
	@Autowired
	ItemsRepository itemsRepository;

	@RequestMapping(path = "/mytwelfth", method = RequestMethod.GET)
	public String twelfth(Model model) {

		//全件検索(findAll)した結果を変数「userlist」にしまっている。
		List<Users> userslist = usersRepository.findAll();

		model.addAttribute("userslist", userslist);

		return "mytwelfth";
	}

	//練習問題用
	@RequestMapping(path = "/mytwelfthitems", method = RequestMethod.GET)
		public String twelfthPra(Model model) {
	
			//全件検索(findAll)した結果を変数「itemslist」にしまっている。
			List<Items> itemslist = itemsRepository.findAll();
	
			model.addAttribute("itemslist", itemslist);
	
			return "mytwelfthitems";
		}

}