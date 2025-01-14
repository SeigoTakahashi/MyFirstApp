package com.example.demo.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.bean.Event;
import com.example.demo.bean.LineData;

@RestController
public class MyLineDbController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//ここにチャンネルアクセストークンを貼る！
	String channelAccessToken = "4IrcAc6vGPWgeLHw44ybiokEqqpagQNjO1kWZROE1RP2Ihd71WL+fDIzGJR/xLGI27Nato5paS8xaDwfmNpKX9qs/CbJV1H3ZT/UWLtn1yYnzLRjm3ncKpG4MF6ZfafbWOHg6l0khXNRKO0xhGkGcQdB04t89/1O/w1cDnyilFU=";

	@PostMapping("/lineDb")
	@CrossOrigin(origins = "*")
	public void postApidata(@RequestBody LineData webhookData) {
		for (Event event : webhookData.getEvents()) {

			String replyToken = event.getReplyToken();
			String replyText = event.getMessage().getText();

			//ユーザIDを取得する。
			String userId = event.getSource().getUserId();

			String numataImg = "https://www.itc.ac.jp/_cms/wp-content/themes/itc1.1.0/assets/img/teacher/img-teacher-numata-s-on.jpg";

			if ("ユーザ登録したい".equals(replyText)) {
				jdbcTemplate.update("INSERT INTO line_sample ( user_id ,user_plan ) VALUES (?,?);", userId,
						"FREE PLAN");
				replyMessage(replyToken, "登録ありがとうございます！");
			} else if ("国際理工GUYSを見たい".equals(replyText)) {
				replyImageMessage(replyToken, numataImg, numataImg);
			} else {
				replyButtonsTemplate(replyToken);
			}
		}
	}

	/*******************************************************************:
	 * ここから↓は今は気にしないでOK!
	 *******************************************************************/
	private void replyImageMessage(String replyToken, String originalContentUrl, String previewImageUrl) {

		// LINE APIのエンドポイント
		String url = "https://api.line.me/v2/bot/message/reply";

		// HTTPヘッダーにChannel Access Tokenを設定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + channelAccessToken);

		// 送信する画像を設定
		Map<String, Object> message = new HashMap<>();
		message.put("type", "image");
		message.put("originalContentUrl", originalContentUrl);
		message.put("previewImageUrl", previewImageUrl);

		// リクエストボディを設定（画像用）
		Map<String, Object> body = new HashMap<>();
		body.put("replyToken", replyToken);
		body.put("messages", Collections.singletonList(message));

		// 画像を送信
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, new HttpEntity<>(body, headers), String.class);

	}

	//文字を送りたい場合はこのメソッドを呼び出す。
	//呼び出す際、第二引数に送りたい文字列を渡す。
	private void replyMessage(String replyToken, String replyText) {
		// LINE APIのエンドポイント
		String url = "https://api.line.me/v2/bot/message/reply";

		// HTTPヘッダーにChannel Access Tokenを設定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + channelAccessToken);

		// 送信するメッセージを設定
		Map<String, Object> message = new HashMap<>();
		message.put("type", "text");
		message.put("text", replyText);

		// リクエストボディを設定
		Map<String, Object> body = new HashMap<>();
		body.put("replyToken", replyToken);
		body.put("messages", Collections.singletonList(message));

		System.out.println("test");

		// HTTPリクエストを送信
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, new HttpEntity<>(body, headers), String.class);
	}

	private void replyButtonsTemplate(String replyToken) {
		// LINE APIのエンドポイント
		String url = "https://api.line.me/v2/bot/message/reply";

		// HTTPヘッダーにChannel Access Tokenを設定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + channelAccessToken);

		// 1つ目のボタン
		Map<String, Object> button1 = new HashMap<>();
		button1.put("type", "message");
		button1.put("label", "ユーザ登録したい");
		button1.put("text", "ユーザ登録したい");

		// 2つ目のボタン
		Map<String, Object> button2 = new HashMap<>();
		button2.put("type", "message");
		button2.put("label", "国際理工GUYSを見たい");
		button2.put("text", "国際理工GUYSを見たい");

		//画面に出したいボタンをリストに詰める。
		List<Map<String, Object>> buttons = Arrays.asList(button1, button2);

		// テンプレートにボタンを追加
		Map<String, Object> template = new HashMap<>();
		template.put("type", "buttons");
		template.put("text", "要件をどうぞー");
		template.put("actions", buttons);

		// メッセージにテンプレートを追加
		Map<String, Object> message = new HashMap<>();
		message.put("type", "template");
		message.put("altText", "ボタンテンプレート");
		message.put("template", template);

		// リクエストボディを設定
		Map<String, Object> body = new HashMap<>();
		body.put("replyToken", replyToken);
		body.put("messages", Collections.singletonList(message));

		// HTTPリクエストを送信
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, new HttpEntity<>(body, headers), String.class);
	}
}

