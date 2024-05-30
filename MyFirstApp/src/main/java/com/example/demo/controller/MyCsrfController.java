package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyCsrfController {

	@RequestMapping(path = "/csrf", method = RequestMethod.GET)
	public String viewPage() {
		return "mycsrf";
	}

	@RequestMapping(path = "/moneySend", method = RequestMethod.GET)
	public String sendMoney(String atesaki, String kingaku, Model model,@RequestParam("_csrf") String csrfToken) {

		model.addAttribute("atesaki", atesaki);
		model.addAttribute("kingaku", kingaku);
		model.addAttribute("_csrf",csrfToken);
		return "mycsrf_result";
	}
}