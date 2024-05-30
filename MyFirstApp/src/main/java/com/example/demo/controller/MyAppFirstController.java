package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyAppFirstController {

	//今は覚えなくて良いけど、余談//
	//SimpleMessagingTemplateはSpringが持つリアルタイム通信のプログラムだよ。
	//メッセージを送信したり、タイムアウトで切断したりする機能を持つよ//
	@Autowired
	private SimpMessagingTemplate template;

	@RequestMapping(path = "/myappfirst", method = RequestMethod.GET)
	public String input1() {
		return "myappfirst";
	}

	/*******!!!!!!!!!!!!↓ここに注目↓!!!!!!!!!!!!!!!!!*******/
	@MessageMapping("/first")
	public void send1(@Payload String msg) {
		//「topic/client1」というチャネルにメッセージを送信
		template.convertAndSend("/topic/client1", msg + "を受信しました。");
	}

	/*********************************************************/

	/*******!!!!!!!!!!!!↓練習問題用↓!!!!!!!!!!!!!!!!!*******/
	@MessageMapping("/webpra")
	public void sendPra1(@Payload String pra1) {
		//「topic/client1」というチャネルにメッセージを送信
		template.convertAndSend("/topic/clientpra1", pra1 + "受信したのでクリア");
	}

	/*********************************************************/

	/*******!!!!!!!!!!!!↓練習問題用↓!!!!!!!!!!!!!!!!!*******/
	@MessageMapping("/webprapra")
	public void sendPra2(@Payload String pra2) {
		//「topic/client1」というチャネルにメッセージを送信
		template.convertAndSend("/topic/clientpra2", pra2 + "受信したのでクリアだひょー");
	}
	/*********************************************************/

}