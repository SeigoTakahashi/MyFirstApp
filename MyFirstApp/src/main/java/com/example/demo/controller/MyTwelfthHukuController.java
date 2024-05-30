package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Books;
import com.example.demo.repository.BooksRepository;

@Controller
public class MyTwelfthHukuController {

	@Autowired
	BooksRepository booksRepository;

	@RequestMapping(path = "/mytwelfthhuku", method = RequestMethod.GET)
	public String twelfth(Model model) {

		//全件検索(findAll)した結果を変数「userlist」にしまっている。
		//ヒント①：今回は何という名前のエンティティを使っているかな？
		//ヒント②：Repository19行目当たりにあるrepositoryの変数名は？
		//ヒント③：Slackに送られたスライドを良く見てみよう。Controllerでのマッチングについて書いてあるね。
		List<Books> bookslist = booksRepository.findAll();

		model.addAttribute("bookslist", bookslist);

		return "mytwelfthhuku";
	}
}