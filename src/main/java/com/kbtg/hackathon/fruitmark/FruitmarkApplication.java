package com.kbtg.hackathon.fruitmark;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
public class FruitmarkApplication {
	public static Path downloadedContentDir;
	
	public static void main(String[] args) throws IOException {
		
		downloadedContentDir = Files.createTempDirectory("line-bot");
		ApplicationContext ctx = SpringApplication.run(FruitmarkApplication.class, args);
		
		System.out.println("Inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println("Bean Definition Name : " + beanName);
		}
	}
	
}
