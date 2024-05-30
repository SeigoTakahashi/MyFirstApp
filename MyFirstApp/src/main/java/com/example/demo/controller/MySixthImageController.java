package com.example.demo.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MySixthImageController {
	@RequestMapping(path = "/mysixthimage", method = RequestMethod.GET)
	public String sixthimage() {
		return "mysixthimage";
	}

	@RequestMapping(path = "/mysixthimage", method = RequestMethod.POST)
	public String sixthimage(MultipartFile myfile,MultipartFile mymy, Model model) throws IOException {
		//アップロードされたファイルをバイトデータに変換する。
		byte[] byteData = myfile.getBytes();
		//Base64に変換する。
		String encodedImage = Base64.getEncoder().encodeToString(byteData);
		//Base64に変換した画像データをmodelに格納する。
		model.addAttribute("encodedImage", encodedImage);
		return "mysixthimage";
	}
}