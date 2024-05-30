package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyAppHukuController {

	//今は覚えなくて良いけど、余談//
	//SimpleMessagingTemplateはSpringが持つリアルタイム通信のプログラムだよ。
	//メッセージを送信したり、タイムアウトで切断したりする機能を持つよ//
	@Autowired
	private SimpMessagingTemplate template;

	@RequestMapping(path = "/myapphuku", method = RequestMethod.GET)
	public String input1() {
		return "myapphuku";
	}

	/*******!!!!!!!!!!!!↓復習問題用↓!!!!!!!!!!!!!!!!!*******/
	//@MessageMappingに書かれたURLは頭に「/app」が付け加えられる。
	@MessageMapping("/hukuhuku")
	public void send1(@Payload String msg) {
		template.convertAndSend("/topic/hukucha", "復習問題クリア：" + msg);
	}
	/*********************************************************/
}