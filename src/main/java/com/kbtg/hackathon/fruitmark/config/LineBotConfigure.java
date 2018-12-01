package com.kbtg.hackathon.fruitmark.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kbtg.hackathon.fruitmark.FruitmarkApplication;

public class LineBotConfigure implements WebMvcConfigurer {
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String downloadedContentUri = FruitmarkApplication.downloadedContentDir.toUri().toASCIIString();
        System.out.println("downloaded Uri: " + downloadedContentUri);
        registry.addResourceHandler("/downloaded/**")
                .addResourceLocations(downloadedContentUri);
    }

}
