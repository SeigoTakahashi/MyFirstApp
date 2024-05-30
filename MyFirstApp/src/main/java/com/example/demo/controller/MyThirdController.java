package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyThirdController {
	@RequestMapping(path = "/mythird", method = RequestMethod.GET)
	public String third(Model model) {
		String x = "3回目だよーーーー。Controllerの変数だよーーー";
		model.addAttribute("sample", x);
		String y = "なれた";
		model.addAttribute("sample2", y);
		return "mythird";
	}
}