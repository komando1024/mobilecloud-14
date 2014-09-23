package org.magnum.mobilecloud.video.service;

import java.util.Collection;

import org.magnum.mobilecloud.video.model.Video;

public interface IVideoService {

	Collection<Video> getVideoList();

	Video addVideo(Video video);

	Video findVideoById(long id);
	
	Collection<Video> findVideoByName(String name);

	Collection<Video> findVideoByDuration(long duration);

}