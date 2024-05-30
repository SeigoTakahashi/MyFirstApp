package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.repository.MessagesRepository;

@Controller
public class MyAppOuyouController {

	
	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	MessagesRepository messagesRepository;

	@RequestMapping(path = "/myappouyou", method = RequestMethod.GET)
	public String input1() {
		return "myappouyou";
	}
	
	@MessageMapping("/sendPower")
	public void send1(@Payload String myPower) {

		int num = Integer.parseInt(myPower);
		if(num < 350) {
			template.convertAndSend("/topic/enemy", myPower);
		} else if(num >= 350) {
			template.convertAndSend("/topic/gameover", "GAME OVER");
		}

	}

}