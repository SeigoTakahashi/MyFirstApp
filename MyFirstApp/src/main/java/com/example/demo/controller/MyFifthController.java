package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyFifthController {
	@RequestMapping(path = "/myfifth", method = RequestMethod.GET)
	public String fifth() {
		return "myfifth";
	}

	@RequestMapping(path = "/myfifth", method = RequestMethod.POST)
	public String fifth(String yourname, String[] yourhobby, String[] cer,String[] fried, String yourblood, String yourbirth,
			Model model) {
		/**
		 * 画面から入力された内容をSysout
		 */
		//名前を表示
		System.out.println("名前：" + yourname);
		//血液型を表示
		System.out.println("血液型：" + yourblood);
		//趣味を表示
		if(yourhobby != null) {
			for (int i = 0; i < yourhobby.length; i++) {
				System.out.println("趣味:" + yourhobby[i]);
			}
		}
		
		//保有資格を表示
		if(cer != null) {
			for (int i = 0; i < cer.length; i++) {
				System.out.println("保有資格:" + cer[i]);
			}
		}
		
		//好きな食べ物を表示
		if(fried != null) {
			for (int i = 0; i < fried.length; i++) {
				System.out.println("好きな食べ物:" + fried[i]);
			}
		}
		
		//誕生日を表示
		System.out.println("誕生日：" + yourbirth);
		/**
		 * HTMLで表示するために、modelに格納する。
		 */
		model.addAttribute("hobbylist", yourhobby);
		model.addAttribute("cerlist", cer);
		model.addAttribute("friedlist", fried);

		return "myfifth";
	}
}