package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyTravController {

	@RequestMapping(path = "/directory/{directoryName}", method = RequestMethod.GET)
	public String readFile(@PathVariable String directoryName) throws IOException {

		//ディレクトリの読み込み
		File file = new File("src/main/resources/" + directoryName);

		//ディレクトリが存在するかチェック。存在するなら内容を表示
		if (file.exists()) {
			return new String(Files.readAllBytes(file.toPath()));
		} else {
			return "File Not Found";
		}
	}
}