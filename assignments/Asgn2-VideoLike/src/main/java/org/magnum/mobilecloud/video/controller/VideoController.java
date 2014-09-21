package org.magnum.mobilecloud.video.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.magnum.mobilecloud.video.client.VideoSvcApi;
import org.magnum.mobilecloud.video.repository.Video;
import org.magnum.mobilecloud.video.service.VideoFileManager;
import org.magnum.mobilecloud.video.service.VideoServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.ResourceAccessException;

@Controller
public class VideoController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(VideoController.class);

	@Resource
	private VideoServiceInterface videoService;

	@RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH, method = RequestMethod.GET)
	public @ResponseBody Collection<Video> getVideoList() {
		LOGGER.info("getVideoList()");

		Collection<Video> videos = videoService.getVideoList();
		return videos;
	}

	@RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH, method = RequestMethod.POST)
	public @ResponseBody Video addVideo(@RequestBody Video inputVideo) {
		LOGGER.info("addVideo()");

		Video video = videoService.addVideo(inputVideo);
		return video;
	}


	@RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH, method = RequestMethod.GET)
	public void getData(@PathVariable long id,
			HttpServletResponse response) {
		LOGGER.info("getData()");

		Video video = videoService.findVideoById(id);
		if (video == null) {
			throw new ResourceNotFoundException("no video with such id");
		}

		try {
			OutputStream out = response.getOutputStream();
			VideoFileManager.get().copyVideoData(video, out);
		} catch (IOException e) {
			LOGGER.error("problem while downloading file {} ", e.getMessage());
			throw new ResourceAccessException(
					"problem while downloading file {}", e);
		}
	}

}
