package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Messages;
import com.example.demo.repository.MessagesRepository;

@Controller
public class MyAppSecondController {

	//今は覚えなくて良いけど、余談//
	//SimpleMessagingTemplateはSpringが持つリアルタイム通信のプログラムだよ。
	//メッセージを送信したり、タイムアウトで切断したりする機能を持つよ//
	@Autowired
	private SimpMessagingTemplate template;

	//練習問題用
	@Autowired
	MessagesRepository messagesRepository;

	@RequestMapping(path = "/myappsecond", method = RequestMethod.GET)
	public String input1() {
		return "myappsecond";
	}

	/*******!!!!!!!!!!!!↓ここに注目↓!!!!!!!!!!!!!!!!!*******/
	@MessageMapping("/second")
	public void send1(@Payload String msg) {

		//messageIDを決めるために、全件検索する。
		//考えてみよう。全件検索は何を使うんだっけ？過去のController見てみて！
		List<Messages> messageslist = messagesRepository.findAll();

		//リストのサイズを見て、リストサイズに+1する事でmessageidとする。
		//例えばDBの中に3件データがあるならリストサイズは3になる。
		//次のデータのIDを4としたいので、3+1する。
		int message_id = messageslist.size() + 1;

		//DBへのsaveするためにインスタンス生成
		Messages message = new Messages();
		message.setMessage_id(message_id);
		message.setMessage_text(msg);

		//DBへの保存
		//考えてみよう
		messagesRepository.save(message);

		//一通りの処理が済んだのでチャネルにメッセージを流す。
		template.convertAndSend("/topic/client2", msg + "を受信しました。このメッセージのIDは" + message.getMessage_id());
	}
	/*********************************************************/

}