package com.kbtg.hackathon.fruitmark.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.io.ByteStreams;
import com.kbtg.hackathon.fruitmark.FruitmarkApplication;
import com.linecorp.bot.client.MessageContentResponse;

public class DownloadedContent {

	private Path path;
    private String uri;
    
    public DownloadedContent() {
		super();
	}

	public DownloadedContent(Path path, String uri) {
		super();
		this.path = path;
		this.uri = uri;
	}

	public DownloadedContent saveContent(String ext, MessageContentResponse response) {
		System.out.println("Content-type: " + response);
		DownloadedContent tempFile = createTempFile(ext);
		try (OutputStream outputStream = Files.newOutputStream(tempFile.path)) {
			ByteStreams.copy(response.getStream(), outputStream);
			System.out.println(String.format("Save %s: %s", ext, tempFile));
			return tempFile;
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	public DownloadedContent createTempFile(String ext) {
	    String fileName = LocalDateTime.now() + "-" 
	                      + UUID.randomUUID().toString() 
	                      + "." + ext;
	    Path tempFile = FruitmarkApplication.downloadedContentDir.resolve(fileName);
	    tempFile.toFile().deleteOnExit();
	    return new DownloadedContent(tempFile, 
	                                 createUri("/downloaded/" + tempFile.getFileName()));

	}
	
    public static String createUri(String path) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(path).toUriString();
    }

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
    
    
	
}
