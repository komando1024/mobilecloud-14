package org.magnum.mobilecloud.video.controller;

import java.util.Collection;

import javax.annotation.Resource;

import org.magnum.mobilecloud.video.client.VideoSvcApi;
import org.magnum.mobilecloud.video.model.Video;
import org.magnum.mobilecloud.video.service.IVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author mejdi
 *
 */
@Controller
public class VideoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(VideoController.class);

	@Resource
	private IVideoService videoService;

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

	@RequestMapping(value = VideoSvcApi.VIDEO_TITLE_SEARCH_PATH, method = RequestMethod.GET)
	public Collection<Video> findVideoByName(@RequestParam(VideoSvcApi.TITLE_PARAMETER) String title){
		LOGGER.info("findVideoByName()");

		Collection<Video> videos = videoService.findVideoByName(title);

		return videos;
	}

	@RequestMapping(value = VideoSvcApi.VIDEO_DURATION_SEARCH_PATH, method = RequestMethod.GET)
	public Collection<Video> findVideoByDuration(@RequestParam(VideoSvcApi.DURATION_PARAMETER) long duration){
		LOGGER.info("findVideoByDuration()");

		Collection<Video> videos = videoService.findVideoByDuration(duration);

		return videos;
	}

	//	@RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH, method = RequestMethod.GET)
	//	public void getData(@PathVariable long id, HttpServletResponse response) {
	//		LOGGER.info("getData()");
	//
	//		Video video = videoService.findVideoById(id);
	//		if (video == null) {
	//			throw new ResourceNotFoundException("no video with such id");
	//		}
	//
	//		try {
	//			OutputStream out = response.getOutputStream();
	//			VideoFileManager.get().copyVideoData(video, out);
	//		} catch (IOException e) {
	//			LOGGER.error("problem while downloading file {} ", e.getMessage());
	//			throw new ResourceAccessException("problem while downloading file {}", e);
	//		}
	//	}
}