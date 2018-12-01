package com.kbtg.hackathon.fruitmark.controller;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;

import com.kbtg.hackathon.fruitmark.service.SearchFruitService;
import com.kbtg.hackathon.fruitmark.service.SearchMerchantService;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import lombok.NonNull;

@LineMessageHandler
public class LineBotController {
	
	@Autowired
	private LineMessagingClient lineMessagingClient;
	
	@EventMapping
	public Message handleTextMessage(MessageEvent<TextMessageContent> e) {
		System.out.println("Class LineBotController");
		System.out.println("Method handleTextMessage");
		System.out.println("Event: " + e);
		
		String text = e.getMessage().getText();
		System.out.println("Text: " + text);
		
		String response = "ไม่รู้จัก";
		if (text.startsWith("ค้นหาร้าน")) {
			// Validate and Prepare Input
			
			// Process Input
			SearchMerchantService service = new SearchMerchantService();
			response = service.searchByName("");
			
			// Build Line Response
		} else if (text.startsWith("ค้นหาสินค้า")) {
			// Validate and Prepare Input
			
			// Process Input
			SearchFruitService service = new SearchFruitService();
			response = service.searchByName("");
			
			// Build Line Response
		} else if (text.startsWith("ทดสอบ")) {
			
		}
		
		return new TextMessage(response);
	}
	
	@EventMapping
	public void handleDefaultMessageEvent(Event event) {
		System.out.println("Class LineBotController");
		System.out.println("Method handleDefaultMessageEvent");
		System.out.println("Event: " + event);
	}
	
//	@EventMapping
//	public void handleStickerMessageEvent(MessageEvent<StickerMessageContent> event) {
//		handleSticker(event.getReplyToken(), event.getMessage());
//	}
	
//	private void handleSticker(String replyToken, StickerMessageContent content) {
//		reply(replyToken, new StickerMessage(content.getPackageId(), content.getStickerId()));
//	}
	
//	private void reply(@NonNull String replyToken, @NonNull Message message) {
//		reply(replyToken, Collections.singletonList(message));
//	}
	
//	private void reply(@NonNull String replyToken, @NonNull List<Message> messages) {
//		try {
//			BotApiResponse apiResponse = lineMessagingClient.replyMessage(new ReplyMessage(replyToken, messages)).get();
//			System.out.printf("Sent messages: {}", apiResponse);
//		} catch (InterruptedException | ExecutionException e) {
//			throw new RuntimeException(e);
//		}
//	}
	
}