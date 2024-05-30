package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyXssController {

	@RequestMapping(path = "/xss", method = RequestMethod.GET)
	public String xssPage() {
		return "myxss";
	}

	@RequestMapping(path = "/xssresult", method = RequestMethod.GET)
	public String submitForm(@RequestParam("message") String message, Model model) {
		model.addAttribute("userMessage", message);
		return "myxss";
	}
}