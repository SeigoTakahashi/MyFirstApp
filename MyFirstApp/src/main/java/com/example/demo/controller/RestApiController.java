//package com.example.demo.controller;
//
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.bean.ApiData;
//
//@RestController
//@CrossOrigin("http://127.0.0.1:5500")
//public class RestApiController {
//	
//	@GetMapping("/javascript1")
//	public ApiData greeting(@RequestParam(value = "name",defaultValue = "test")String name) {
//		return new ApiData(1,name);
//	}
//
//}
