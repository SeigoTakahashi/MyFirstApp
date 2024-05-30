package com.example.demo.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ApiData;
import com.example.demo.bean.ApiDataPra;
import com.example.demo.bean.Gacha;

@RestController
public class MyApiController {

	@PostMapping("/api")
	@CrossOrigin(origins = "*")
	public ApiData postApidata(@RequestBody ApiData apiData) {
		//変数「apiData」に受信したデータが格納されている。

		//apiDataからmessageを取り出し。
		String jusinMessage = apiData.getMessage();

		//HTMLに返すデータ。
		return new ApiData("HTML→Spring(ここで作った文章です): " + apiData.getMessage(), 0);
	}

	//慣れよう問題用
	@PostMapping("/apinare")
	@CrossOrigin(origins = "*")
	public ApiData postApidatanare(@RequestBody ApiData apiData) {
		//変数「apiData」に受信したデータ（Json）が格納されている。
		//apiDataからmessageを取り出し。
		String jusinMessage = apiData.getMessage();

		//apiDataからquantityを取り出してみよう。
		int jusinQuantity = apiData.getQuantity();

		//取り出したmessageとquantityをHTMLに帰してみよう。
		return new ApiData("復習問題です。: " + apiData.getMessage(), apiData.getQuantity());
	}

	//練習問題用
	@PostMapping("/apiPra")
	@CrossOrigin(origins = "*")
	public ApiDataPra postApidataPra(@RequestBody ApiDataPra apiData) {
		//変数「apiData」に受信したデータ（Json）が格納されている。

		//受信データからAPIキーを取得
		String jusinApiKey = apiData.getApiKey();

		//取得したAPIキーが正しい「kokuri1920」かどうかによって返すメセージを変える。
		String returnMoji = "";
		if ("kokuri1920".equals(jusinApiKey)) {
			returnMoji = "正しいAPIキーが入力されました。開発者情報にアクセスが出来ます。";
		} else {
			returnMoji = "間違ったAPIキーです。サービス提供者にAPIキーを問い合わせてください。";
		}

		//取り出したmessageとquantityをHTMLに帰してみよう。
		return new ApiDataPra(jusinApiKey + "です。問い合わせの結果は…:" + returnMoji);
	}

	//練習問題用
	@PostMapping("/apiGacha")
	@CrossOrigin(origins = "*")
	public Gacha postApidataGacha() {
		//1-100のランダムな数値を生成し、変数「ransu」に格納する。
		Random rnd = new Random();
		int ransu = rnd.nextInt(100) + 1;

		//return用の変数
		String name = "";
		String rarerity = "";
		String imgUrl = "";
		//100%渡部が出る。地獄のような仕様
		if (ransu <= 50 && ransu >= 1) {
			name = "渡部";
			rarerity = "SSR";
			imgUrl = "https://www.itc.ac.jp/_cms/wp-content/themes/itc1.1.0/assets/img/teacher/img-teacher-watabe-m-on.jpg";
		} else if(ransu <= 90 && ransu >= 51){
			name = "末原";
			rarerity = "R";
			imgUrl = "https://www.itc.ac.jp/_cms/wp-content/themes/itc1.1.0/assets/img/teacher/img-teacher-suehara-a-on.jpg";
		} else if(ransu <= 100 && ransu >= 91) {
			name = "竹野谷";
			rarerity = "SR";
			imgUrl = "https://www.itc.ac.jp/_cms/wp-content/themes/itc1.1.0/assets/img/teacher/img-teacher-takenoya-y-on.jpg";
		}
		return new Gacha(name, rarerity, imgUrl);
	}
}