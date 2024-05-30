package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.bean.MyAppImage;

@Controller
public class MyAppImageController {

	@Autowired
	private SimpMessagingTemplate template;

	@RequestMapping(path = "/myappimage", method = RequestMethod.GET)
	public String input1() {
		return "myappimage";
	}

	/*******!!!!!!!!!!!!↓ここに注目!!!!!!!!!!!!!!!!!*******/
	@MessageMapping("/sendImage")
	public void send1(@Payload MyAppImage sendData) {

		System.out.println(sendData.getName());
		System.out.println(sendData.getMsg());
		System.out.println(sendData.getImageBase64());

		template.convertAndSend("/topic/processImage2", sendData);
	}
	/*********************************************************/
}