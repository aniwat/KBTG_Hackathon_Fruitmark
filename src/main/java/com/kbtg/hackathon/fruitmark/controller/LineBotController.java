package com.kbtg.hackathon.fruitmark.controller;

import static java.util.Arrays.asList;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;

import com.kbtg.hackathon.fruitmark.azure.image.ImageAnalyze;
import com.kbtg.hackathon.fruitmark.dao.MerchantRepository;
import com.kbtg.hackathon.fruitmark.entity.Merchant;
import com.kbtg.hackathon.fruitmark.line.CatalogueFlexMessageSupplier;
import com.kbtg.hackathon.fruitmark.line.MerchantCatalogueFlexMessageSupplier;
import com.kbtg.hackathon.fruitmark.line.ProductCatalogueFlexMessageSupplier;
import com.kbtg.hackathon.fruitmark.service.SearchFruitService;
import com.kbtg.hackathon.fruitmark.utils.DownloadedContent;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.MessageContentResponse;
//import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.CameraAction;
import com.linecorp.bot.model.action.CameraRollAction;
import com.linecorp.bot.model.action.LocationAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.quickreply.QuickReply;
import com.linecorp.bot.model.message.quickreply.QuickReplyItem;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import lombok.NonNull;

@LineMessageHandler
public class LineBotController {
	
	@Autowired
	private LineMessagingClient lineMessagingClient;
	
	@Autowired
	MerchantRepository merchantRepo;
	
	@EventMapping
	public void handleTextMessage(MessageEvent<TextMessageContent> event) {
		System.out.println("Class LineBotController");
		System.out.println("Method handleTextMessage");
		System.out.println("Event: " + event);
		
		String replyToken = event.getReplyToken();
		System.out.println("replyToken = " + replyToken);
		
		String text = event.getMessage().getText();
		System.out.println("text: " + text);
		handleText(text, replyToken);
	}
	
	@EventMapping
	public void handleDefaultMessageEvent(Event event) {
		System.out.println("Class LineBotController");
		System.out.println("Method handleDefaultMessageEvent");
		System.out.println("Event: " + event);
	}
	
	@EventMapping
	public void handleStickerMessageEvent(MessageEvent<StickerMessageContent> event) {
		handleSticker(event.getReplyToken(), event.getMessage());
	}
	
	private void handleSticker(String replyToken, StickerMessageContent content) {
		reply(replyToken, new StickerMessage(content.getPackageId(), content.getStickerId()));
	}
	
	private void reply(@NonNull String replyToken, @NonNull Message message) {
		reply(replyToken, Collections.singletonList(message));
	}
	
	private void reply(@NonNull String replyToken, @NonNull List<Message> messages) {
		try {
			BotApiResponse apiResponse = lineMessagingClient.replyMessage(new ReplyMessage(replyToken, messages)).get();
			System.out.printf("Sent messages: {}", apiResponse);
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}
	
	@EventMapping
	public void handleImageMessage(MessageEvent<ImageMessageContent> event) {
		System.out.println(event.toString());
		ImageMessageContent content = event.getMessage();
		String replyToken = event.getReplyToken();
		
		try {
			MessageContentResponse response = lineMessagingClient.getMessageContent(content.getId()).get();
			DownloadedContent downloadedContent = new DownloadedContent();
			DownloadedContent jpg = downloadedContent.saveContent("jpg", response);
			
			ImageAnalyze image = new ImageAnalyze();
			String keyW0rd = image.analzye(jpg.getUri());
			
			handleText(keyW0rd, replyToken);
			
		} catch (InterruptedException | ExecutionException e) {
			handleText("ไม่รู้จัก", replyToken);
			throw new RuntimeException(e);
		}
	}
	
	private void handleText(String text, String replyToken) {
		String response = "ไม่รู้จักคำสั่ง";
		if (text.startsWith("หาร้านค้า")) {
			// Validate and Prepare Input
			String[] words = text.split(" ");
			String searchName = "";
			if (words.length >= 2) {
				searchName = words[1];
			}
			
			System.out.println("searchName = " + searchName);
			
			// Process Input
			Iterable<Merchant> list = null;
			if (searchName.length() > 0) {
				list = merchantRepo.findByMerchantName(searchName);
			}
			
			// Build Line Response
			response = "ไม่พบร้านค้า";
			if (list != null) {
				response = "";
				for (Merchant merchant : list) {
					response += merchant.getMerchantName() + ",";
				}
			}
			
			System.out.println("response = " + response);
		} else if (text.startsWith("หาสินค้า")) {
			// Validate and Prepare Input
			
			// Process Input
			SearchFruitService service = new SearchFruitService();
			response = service.searchByName("");
			
			// Build Line Response
		} else if (text.startsWith("ดูหน่อย")) {
			this.reply(replyToken, new CatalogueFlexMessageSupplier().get());
		} else if (text.toLowerCase().startsWith("m1")) {
			this.reply(replyToken, new MerchantCatalogueFlexMessageSupplier().get());
		} else if (text.toLowerCase().startsWith("p1")) {
			this.reply(replyToken, new ProductCatalogueFlexMessageSupplier().get());
		} else {
			List<QuickReplyItem> items = asList(
			    // Take photo
			    QuickReplyItem.builder().action(CameraAction.withLabel("ลองถ่ายรูปไหม?")).imageUrl(URI.create("https://example.com/image.png")).build(),
			    // Choose from gallery
			    QuickReplyItem.builder().action(CameraRollAction.withLabel("หาจากรูป")).build(),
			    // Send Location
			    QuickReplyItem.builder().action(LocationAction.withLabel("Location")).build());
			this.reply(replyToken, TextMessage.builder().text("เมนูช่วยเหลือ").quickReply(QuickReply.items(items)).build());
		}
		
		this.reply(replyToken, new TextMessage(response));
		
	}
	
}