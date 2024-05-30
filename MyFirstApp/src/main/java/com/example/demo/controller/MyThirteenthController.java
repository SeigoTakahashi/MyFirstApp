package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Books;
import com.example.demo.entity.Drinks;
import com.example.demo.repository.BooksRepository;
import com.example.demo.repository.DrinksRepository;

@Controller
public class MyThirteenthController {

	//リポジトリ使うよ宣言
	@Autowired
	BooksRepository booksRepository;

	//練習問題用
	@Autowired
	DrinksRepository drinksRepository;
	//自由に決めてOK;

	//画面表示用
	@RequestMapping(path = "/mythirteen", method = RequestMethod.GET)
	public String thirteen(Model model) {

		//全件検索(findAll)した結果を変数「userlist」にしまっている。
		List<Books> bookslist = booksRepository.findAll();

		model.addAttribute("bookslist", bookslist);

		return "mythirteen";
	}

	//登録＆更新用メソッド
	@RequestMapping(path = "/mythirteenInsUpd", method = RequestMethod.POST)
	public String thirteenInsUpd(Model model, String book_id, String book_name, String book_page, String author) {

		//HTMLから送られてきたIDをintに変換。(引数でintとして受け取ってもOK)
		//book_idとbook_pageはDBの定義がint型なので、変換する必要あり。
		int bid = Integer.parseInt(book_id);
		int bpage = Integer.parseInt(book_page);

		//Booksエンティティのインスタンスを作って、
		//登録したい or 更新したい値をセットする。
		Books myBook = new Books();

		myBook.setBook_id(bid);
		myBook.setBook_name(book_name);
		myBook.setBook_page(bpage);
		myBook.setAuthor(author);

		//Repositoryを介してDBに登録
		//登録 or 更新の時はsaveメソッドを使う。
		booksRepository.save(myBook);

		return "redirect:/mythirteen";
	}

	//削除用メソッド
	@RequestMapping(path = "/mythirteenDelete", method = RequestMethod.POST)
	public String thirteenDel(String book_id) {

		//HTMLから送られてきたIDをintに変換。(引数でintとして受け取ってもOK)
		int bid = Integer.parseInt(book_id);

		//repositoryを介してDBから削除.
		//削除はdeleteByIdメソッドを使う。
		booksRepository.deleteById(bid);

		//DB全件削除のやべー奴
		//booksRepository.deleteAll();

		return "redirect:/mythirteen";
	}

	//練習問題用(画面表示)

	@RequestMapping(path = "/mythirteenpra", method = RequestMethod.GET)
	public String thirteenPraDisp(Model model) {

		//全件検索(findAll)した結果を変数「userlist」にしまっている。
		List<Drinks> userlist = drinksRepository.findAll();

		model.addAttribute("drinkslist", userlist);

		return "mythirteenpra";
	}

	//練習問題用(登録＆更新用メソッド)

	@RequestMapping(path = "/mythirteenpraIns", method = RequestMethod.POST)
	public String thirteenPraInsUpd(String drink_id,String drink_name,String drink_price) {

		//Booksエンティティのインスタンスを作って、
		//登録したい or 更新したい値をセットする。
		int did = Integer.parseInt(drink_id);
		int dprice = Integer.parseInt(drink_price);
		Drinks drinks = new Drinks();
		drinks.setDrink_id(did);
		drinks.setDrink_name(drink_name);
		drinks.setDrink_price(dprice);
		//Repositoryを介してDBに登録
		//登録 or 更新の時はsaveメソッドを使う。
		drinksRepository.save(drinks);

		return "redirect:/mythirteenpra";
	}

	//練習問題用(削除用メソッド)

	@RequestMapping(path = "/mythirteenPraDel", method = RequestMethod.POST)
		public String thirteenPraDel(String drink_id) {
	
			
			int did = Integer.parseInt(drink_id);
			//repositoryを介してDBから削除.
			//削除はdeleteByIdメソッドを使う。
			drinksRepository.deleteById(did);
	
			return "redirect:/mythirteenpra";
		}

}