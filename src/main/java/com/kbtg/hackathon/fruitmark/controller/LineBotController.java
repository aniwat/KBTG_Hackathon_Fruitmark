package com.kbtg.hackathon.fruitmark.controller;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;

import com.kbtg.hackathon.fruitmark.azure.image.ImageAnalyze;
import com.kbtg.hackathon.fruitmark.dao.MerchantRepository;
import com.kbtg.hackathon.fruitmark.entity.Merchant;
import com.kbtg.hackathon.fruitmark.line.CatalogueFlexMessageSupplier;
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
import com.linecorp.bot.model.message.ImageMessage;
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
		
		String text = event.getMessage().getText();
		System.out.println("Text: " + text);
		
		String response = "������ѡ�����";
		if (text.startsWith("����ҹ���")) {
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
			response = "��辺��ҹ���";
			if (list != null) {
				for (Merchant merchant : list) {
					response += merchant.getMerchantName() + ",";
				}
			}
			
			System.out.println("response = " + response);
		} else if (text.startsWith("���Թ���")) {
			// Validate and Prepare Input
			
			// Process Input
			SearchFruitService service = new SearchFruitService();
			response = service.searchByName("");
			
			// Build Line Response
		} else if (text.startsWith("���ͺ")) {
			List<QuickReplyItem> items = asList(QuickReplyItem.builder().action(CameraAction.withLabel("Action Label")).imageUrl(URI.create("https://example.com/image.png")).build(),
			    QuickReplyItem.builder().action(CameraRollAction.withLabel("Roll Action Label")).build(),
			    QuickReplyItem.builder().action(LocationAction.withLabel("Location Action")).build());
			
			TextMessage target = TextMessage.builder().text("TEST").quickReply(QuickReply.items(items)).build();
			// return target;
			this.reply(event.getReplyToken(), target);
		} else if (text.startsWith("��˹���")) {
			this.reply(event.getReplyToken(), new CatalogueFlexMessageSupplier().get());
		}
		
		this.reply(event.getReplyToken(), new TextMessage(response));
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
			DownloadedContent previewImage = downloadedContent.createTempFile("jpg");
			
			system("convert", "-resize", "240x", jpg.getPath().toString(), previewImage.getPath().toString());
			
			ImageAnalyze image = new ImageAnalyze();
			String keyW0rd = image.analzye(previewImage.getUri());
			System.out.println("WORD :  " + keyW0rd);
			this.reply(event.getReplyToken(), new TextMessage(keyW0rd));
//			reply(replyToken, new ImageMessage(jpg.getUri(), previewImage.getUri()));
			
		} catch (InterruptedException | ExecutionException e) {
//			reply(replyToken, new TextMessage("Cannot get image: " + content));
			throw new RuntimeException(e);
		}
	}
	
	private void system(String... args) {
		ProcessBuilder processBuilder = new ProcessBuilder(args);
		try {
			Process start = processBuilder.start();
			int i = start.waitFor();
			System.out.println(String.format("result: %s => %s", Arrays.toString(args), i));
		} catch (InterruptedException e) {
			System.out.println("Interrupted " + e);
			Thread.currentThread().interrupt();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
}