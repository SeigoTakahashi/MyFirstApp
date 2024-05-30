package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(path = "/mylogin", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(path = "/mylogin", method = RequestMethod.POST)
	public String login(String ID,String PW) {
		if(("kokuri".equals(ID)) && ("1920".equals(PW))) {
			return "redirect:/myhome";
		} else {
			return "redirect:/myng";
		}
		
		
	}
	@RequestMapping(path = "/myhome", method = RequestMethod.GET)
	public String home(Model model)  {
		List<Map<String, Object>> resultList;
		resultList = jdbcTemplate.queryForList("select * from sns");
		model.addAttribute("selectResult", resultList);
		return "home";
	}
	
	@RequestMapping(path = "/myng", method = RequestMethod.GET)
	public String ng()  {	
		
		return "ng";
	}
}