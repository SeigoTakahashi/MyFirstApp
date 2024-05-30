package com.example.demo.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	
	@RequestMapping(path = "/myhome", method = RequestMethod.POST)
	public String home(MultipartFile myfile, String text) throws IOException {

		byte[] byteData = myfile.getBytes();

		String encodedImage = Base64.getEncoder().encodeToString(byteData);

		String ContentType = myfile.getContentType();
		
		jdbcTemplate.update("INSERT INTO sns (text,image,ContentType) VALUES(?,?,?);", text, encodedImage,ContentType);

		return "redirect:/myhome";
	}
}