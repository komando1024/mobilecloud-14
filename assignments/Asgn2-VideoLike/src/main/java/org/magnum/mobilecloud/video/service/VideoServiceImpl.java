package org.magnum.mobilecloud.video.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.magnum.mobilecloud.video.model.Video;
import org.magnum.mobilecloud.video.repository.IVideoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.collect.Lists;

@Service
public class VideoServiceImpl implements IVideoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(VideoServiceImpl.class);

	@Resource
	private IVideoRepository videoRepository;

	@Override
	public Collection<Video> getVideoList() {
		LOGGER.info("getVideoList");
		
		return Lists.newArrayList(videoRepository.findAll());
	}

	@Override
	public Video addVideo(Video video) {
		LOGGER.info("addVideo");

		video.setUrl(getDataUrl(video.getId()));
		Video myVideo = videoRepository.save(video);

		return myVideo;
	}

	@Override
	public Video findVideoById(long id) {
		LOGGER.info("findVideoById");

		Video myVideo = videoRepository.findOne(id);
		
		return myVideo;
	}

	@Override
	public Collection<Video> findVideoByName(String name) {
		LOGGER.info("findVideoName");

		return videoRepository.findByName(name);
	}

	@Override
	public Collection<Video> findVideoByDuration(long duration) {
		LOGGER.info("findVideoByDuration");

		return videoRepository.findByDurationLessThanEqual(duration);
	}

	private static String getDataUrl(long videoId) {
		String url = getUrlBaseForLocalServer() + "/video/" + videoId + "/data";
		return url;
	}

	private static String getUrlBaseForLocalServer() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String base = "http://"
				+ request.getServerName()
				+ ((request.getServerPort() != 80) ? ":"
						+ request.getServerPort() : "");
		return base;
	}
}
