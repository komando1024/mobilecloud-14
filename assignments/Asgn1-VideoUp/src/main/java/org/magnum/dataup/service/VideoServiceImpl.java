package org.magnum.dataup.service;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.magnum.dataup.model.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.collect.Lists;

@Service
public class VideoServiceImpl implements VideoServiceInterface {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(VideoServiceImpl.class);

	private Collection<Video> videos = Lists.newCopyOnWriteArrayList();

	private AtomicLong id = new AtomicLong();

	@Override
	public Collection<Video> getVideoList() {
		LOGGER.info("getVideoList");
		return videos;
	}

	@Override
	public Video addVideo(Video video) {
		LOGGER.info("addVideo");
		long videoId = id.incrementAndGet();
		video.setId(videoId);
		video.setDataUrl(getDataUrl(videoId));

		boolean value = videos.add(video);
		LOGGER.debug("addVdeo {}", value);

		return video;
	}

	@Override
	public Video findVideoById(long id) {
		LOGGER.info("findVideoById");
		Video myVideo = null;

		for (Video video : videos) {
			if (video.getId() == id) {
				myVideo = video;
				break;
			}
		}

		return myVideo;
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
