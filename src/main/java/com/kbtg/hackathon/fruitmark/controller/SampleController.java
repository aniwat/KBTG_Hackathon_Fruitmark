package com.kbtg.hackathon.fruitmark.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	
	@RequestMapping("/")
	public String index() {
		return "sample";
	}
	
}
