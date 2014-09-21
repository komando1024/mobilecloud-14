package org.magnum.dataup;

import java.util.UUID;

import org.magnum.dataup.model.Video;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestData {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static Video randomVideo() {
		// Information about the video
		// Construct a random identifier using Java's UUID class
		String id = UUID.randomUUID().toString();
		String title = "Video-"+id;
		String subject = "learning";
		String contentType = "mp4";
		String dataUrl = "http://localhost:8080/video/1/data";
		long duration = 60 * (int)Math.rint(Math.random() * 60) * 1000; // random time up to 1hr
		
		Video video =  Video.create()
			.withTitle(title)
			.withSubject(subject)
			.withDuration(duration)
			.withContentType(contentType)
			.build();		
		return video;
	}

	public static String toJson(Object o) throws Exception{
		return objectMapper.writeValueAsString(o);
	}
}
