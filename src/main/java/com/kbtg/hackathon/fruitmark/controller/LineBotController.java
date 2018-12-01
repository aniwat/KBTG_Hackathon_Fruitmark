package com.kbtg.hackathon.fruitmark.controller;

import static java.util.Arrays.asList;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;

import com.kbtg.hackathon.fruitmark.azure.image.ImageAnalyze;
import com.kbtg.hackathon.fruitmark.dao.MerchantRepository;
import com.kbtg.hackathon.fruitmark.dao.ProductRepository;
import com.kbtg.hackathon.fruitmark.entity.Merchant;
import com.kbtg.hackathon.fruitmark.entity.Product;
import com.kbtg.hackathon.fruitmark.line.CatalogueFlexMessageSupplier;
import com.kbtg.hackathon.fruitmark.line.MerchantCatalogueFlexMessageSupplier;
import com.kbtg.hackathon.fruitmark.line.ProductCatalogueFlexMessageSupplier;
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
	
	@Autowired
	ProductRepository productRepo;
	
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
			System.out.printf("Sent messages: {}", apiResponse.getMessage());
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
			
			System.out.println("keyW0rd = " + keyW0rd);
			ArrayList<String> findWord = new ArrayList<String>();
			if (keyW0rd != null && keyW0rd.trim().length() != 0) {
				String[] sp = keyW0rd.split(" ");
				if (sp != null && sp.length > 0) {
					for (String s : sp) {
						if (s.length() >= 3) {
							findWord.add(s);
						}
					}
				}
			}
			
			if (findWord.size() > 0) {
				List<Product> list = null;
				
				for (int i = 0; i < findWord.size(); i++) {
					String find = findWord.get(i);
					List<Product> tempList = null;
					try {
						tempList = productRepo.findPrdByKeyword("%" + find + "%");
						list.addAll(tempList);
					} catch (Exception e) {
						e.printStackTrace();
						this.reply(replyToken, new TextMessage("พังนะจ๊ะ"));
					}
				}
				
				// Build Line Response
				if (list != null && list.size() > 0) {
					ProductCatalogueFlexMessageSupplier pc = new ProductCatalogueFlexMessageSupplier();
					pc.setList(list);
					this.reply(replyToken, pc.get());
				} else {
					this.reply(replyToken, new TextMessage("ไม่พบข้อมูลสินค้า"));
				}
			} else {
				this.reply(replyToken, new TextMessage("ไม่พบข้อมูลสินค้า"));
			}
		} catch (InterruptedException | ExecutionException e) {
			this.reply(replyToken, new TextMessage("พังนะจ๊ะ"));
			throw new RuntimeException(e);
		}
	}
	
	private void handleText(String text, String replyToken) {
		if (text.startsWith("หาร้านค้า")) {
			// Validate and Prepare Input
			String[] words = text.split(" ");
			String searchName = "";
			if (words.length >= 2) {
				searchName = words[1];
			}
			
			System.out.println("searchName = " + searchName);
			
			// Process Input
			List<Merchant> list = null;
			if (searchName.length() > 0) {
				try {
					list = merchantRepo.findByMerchantName("%" + searchName.trim() + "%");
				} catch (Exception e) {
					e.printStackTrace();
					this.reply(replyToken, new TextMessage("พังนะจ๊ะ"));
				}
			}
			
			// Build Line Response
			if (list != null && list.size() > 0) {
				MerchantCatalogueFlexMessageSupplier mc = new MerchantCatalogueFlexMessageSupplier();
				mc.setList(list);
				this.reply(replyToken, mc.get());
			} else {
				this.reply(replyToken, new TextMessage("ไม่พบข้อมูลร้านค้า"));
			}
		} else if (text.startsWith("หาสินค้า")) {
			// Validate and Prepare Input
			String[] words = text.split(" ");
			String searchName = "";
			if (words.length >= 2) {
				searchName = words[1];
			}
			
			System.out.println("searchName = " + searchName);
			
			// Process Input
			List<Product> list = null;
			if (searchName.length() > 0) {
				try {
					list = productRepo.findPrdByKeyword("%" + searchName.trim() + "%");
				} catch (Exception e) {
					e.printStackTrace();
					this.reply(replyToken, new TextMessage("พังนะจ๊ะ"));
				}
			}
			
			// Build Line Response
			if (list != null && list.size() > 0) {
				ProductCatalogueFlexMessageSupplier pc = new ProductCatalogueFlexMessageSupplier();
				pc.setList(list);
				this.reply(replyToken, pc.get());
			} else {
				this.reply(replyToken, new TextMessage("ไม่พบข้อมูลสินค้า"));
			}
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
	}
	
}