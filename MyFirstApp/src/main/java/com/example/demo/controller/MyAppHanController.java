package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.bean.MyAppImage;
import com.example.demo.entity.Hannou;
import com.example.demo.repository.HannouRepository;

@Controller
public class MyAppHanController {

	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	private HannouRepository hannouRepository;

	//DB接続用//

	@RequestMapping(path = "/myapphan", method = RequestMethod.GET)
	public String input1() {
		return "myapphan";
	}

	/*******反応するボタンを押したときの処理*******/
	@MessageMapping("/hannou")
	public void send1(@Payload String pname) {
		//反応した時刻をミリ秒単位で取得して変数「hanTime」に格納
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");
		String hanTime = sdf.format(now);

		template.convertAndSend("/topic/hannou", pname + "が" + hanTime + "に反応した！");
		
		List<Hannou> hanList = hannouRepository.findAll();
		Hannou hannou = new Hannou();
		hannou.setData_id(hanList.size() + 1);
		hannou.setCategory("反応");
		hannou.setUser_name(pname);
		hannou.setTime(hanTime);
		
		hannouRepository.save(hannou);
	}

	/*********************************************************/

	/*******!!!!!!!!!!!!↓ここに注目↓!!!!!!!!!!!!!!!!!*******/
	@MessageMapping("/sikake")
	public void send2(@Payload MyAppImage sendData) {
		//反応した時刻をミリ秒単位で取得して変数「hanTime」に格納
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");
		String hanTime = sdf.format(now);
		
		List<Hannou> hanList = hannouRepository.findAll();
		
		Hannou hannou = new Hannou();
		hannou.setData_id(hanList.size() + 1);
		hannou.setCategory("仕掛け");
		hannou.setUser_name(sendData.getName());
		hannou.setTime(hanTime);
		hannou.setFace(sendData.getImageBase64());
		
		hannouRepository.save(hannou);
		
		template.convertAndSend("/topic/sikake", sendData.getName() + "が" + hanTime + "に仕掛けた！");
	}

	/*********************************************************/

	/*******!!!!!!!!!!!!↓ここに注目↓!!!!!!!!!!!!!!!!!*******/
	@RequestMapping(path = "/myapphanlog", method = RequestMethod.GET)
	public String send2(Model model) {
		List<Hannou> hanList = hannouRepository.findAll();

		model.addAttribute("list", hanList);
		
		return "myapphanlog";
	}
	/*********************************************************/

}