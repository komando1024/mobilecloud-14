package org.magnum.dataup;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;
import org.magnum.dataup.model.Video;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;

public class VideoRestTest {

	private final String TEST_URL = "http://localhost:8080";

	private VideoSvcApi videoService = new RestAdapter.Builder()
			.setEndpoint(TEST_URL)
			.setLogLevel(LogLevel.FULL).build()
			.create(VideoSvcApi.class);

	@Test
	public void testVideoAddAndList() throws Exception {
		
		//create video
		Video video = TestData.randomVideo();

		// Add the video
		Video myVideo = videoService.addVideo(video);
		assertNotNull(myVideo);

		// We should get back the video that we added above
		Collection<Video> videos = videoService.getVideoList();
		assertTrue(videos.contains(video));
	}

}
