package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyFourthFinalController {
	@RequestMapping(path = "/fourthfinal", method = RequestMethod.GET)
	public String third() {
		return "myfourthfinal";
	}

	@RequestMapping(path = "/fourthfinal", method = RequestMethod.POST)
	public String third(Model model ,String yourname) {
		model.addAttribute("sample",yourname);
		
		return "myfourthfinal";
	}
	
}
