package com.kbtg.hackathon.fruitmark.controller;

import com.kbtg.hackathon.fruitmark.service.SearchFruitService;
import com.kbtg.hackathon.fruitmark.service.SearchMerchantService;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@LineMessageHandler
public class LineBotController {
	
	@EventMapping
	public Message handleTextMessage(MessageEvent<TextMessageContent> e) {
		System.out.println("Call LineBotController");
		System.out.println("Event: " + e);
		
		String text = e.getMessage().getText();
		System.out.println("Text: " + text);
		
		String response = "ไม่รู้จัก";
		if (text.startsWith("หาร้าน")) {
			SearchMerchantService service = new SearchMerchantService();
			response = service.searchByName("");
		} else if (text.startsWith("หาผลไม้")) {
			SearchFruitService service = new SearchFruitService();
			response = service.searchByName("");
		}
		
		return new TextMessage(response);
	}
	
}