package com.kbtg.hackathon.fruitmark.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineSampleController {
	
	@RequestMapping("/line")
	public String index(@RequestBody String body, @RequestHeader HttpHeaders headers) {
		System.out.println("Call LineSampleController");
		System.out.println("============= HEADER =============");
		System.out.println(headers);
		System.out.println("==================================");
		System.out.println("============== BODY ==============");
		System.out.println(body);
		System.out.println("==================================");
		return body;
	}
	
}
