package com.kbtg.hackathon.fruitmark.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	
	@RequestMapping("/")
	public String index() {
		System.out.println("Call SampleController");
		return "sample";
	}
	
}
