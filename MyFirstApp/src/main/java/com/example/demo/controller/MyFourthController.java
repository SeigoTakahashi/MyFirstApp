package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyFourthController {
	@RequestMapping(path = "/myfourth", method = RequestMethod.GET)
	public String third() {
		return "myfourth";
	}

	@RequestMapping(path = "/myfourth", method = RequestMethod.POST)
	public String third(Model model ,String yourname, String myname, String practice) {
		System.out.println(yourname);
		System.out.println(myname);
		System.out.println(practice);
		model.addAttribute("sample1",yourname);
		model.addAttribute("sample2",myname);
		model.addAttribute("sample3",practice);
		return "myfourth";
	}
	
}
