package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyAppController {
	@Autowired
	private SimpMessagingTemplate template;

	/**
	 * 画面表示メソッド
	 * URLが/myappの時、myapp.htmlが表示される。
	 */
	@RequestMapping(path = "/myapp", method = RequestMethod.GET)
	public String input1() {
		return "myapp";
	}

	/**
	 * 解答するボタンがおされたときに動くメソッド。
	 * メッセージを「/topic/client1」につないでる人全員に送る。
	 */
	@MessageMapping("/web1")
	public void send1(@Payload String msg) {
		//クライアント(同じ部屋にいる人)にメッセージを送信
		template.convertAndSend("/topic/client1", "【解答】" + msg);
	}

	/**
	 * 出題するボタンがおされたときに動くメソッド
	 * 問題の文字を一部伏字にして送る。
	 */
	//訳あって↓は「/app/odai」と解釈される。
	@MessageMapping("/odai")
	public void odai(@Payload String odai) {

		System.out.println(odai);
		//入力されたワードに伏字を入れる。
		String huseOdai = rndMoji(odai);
		//クライアント(同じ部屋にいる人)にメッセージを送信
		template.convertAndSend("/topic/client1", "【出題だよ】：" + huseOdai);
	}

	/**
	 * 伏字生成メソッド
	 */
	static String rndMoji(String odai) {
		//お題の文字数を取得
		int mojisu = odai.length();

		//String文字列を1文字ずつ生成できるクラス「StringBuilder」のインスタンス「result」生成
		StringBuilder result = new StringBuilder();

		//文字数文ループして伏字にする。
		for (int i = 0; i < mojisu; i++) {
			char currentChar;
			if ((i % 2) == 0) {
				currentChar = '*'; // 伏字にする文字
			} else {
				currentChar = odai.charAt(i);
			}
			result.append(currentChar);
		}
		//文字数文ループ
		return result.toString();
	}

}