package org.magnum.dataup;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.magnum.dataup.controller.VideoController;
import org.magnum.dataup.model.Video;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
public class VideoControllerTest {

	@Resource
	private VideoController videoService;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(videoService).build();
	}
	
	@Test
	public void testVideoAddAndList() throws Exception {
		Video video = TestData.randomVideo();
		video.setId(1L);
		video.setDataUrl("http://localhost:8080/video/1/data");
		String videoJson = TestData.toJson(video);
		
		// Send a request that should invoke VideoSvc.addVideo(Video v)
		// and check that the request succeeded
		mockMvc.perform(
				post(VideoSvcApi.VIDEO_SVC_PATH)
				.contentType(MediaType.APPLICATION_JSON)
	            .content(videoJson))
	            .andExpect(status().isOk())
	            .andReturn();
		
		// Send a request that should invoke VideoSvc.getVideos()
		// and check that the Video object that we added above (as JSON)
		// is in the list of returned videos
		mockMvc.perform(
				get(VideoSvcApi.VIDEO_SVC_PATH))
	            .andExpect(status().isOk())
	            .andExpect(content().string(containsString(videoJson)))
	            .andReturn();
	}

}
