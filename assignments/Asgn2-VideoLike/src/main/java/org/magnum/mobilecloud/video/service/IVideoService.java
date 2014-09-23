package org.magnum.mobilecloud.video.service;

import java.util.Collection;

import org.magnum.mobilecloud.video.repository.Video;

public interface VideoServiceInterface {

	public Collection<Video> getVideoList();

	public Video addVideo(Video video);

	public Video findVideoById(long id);

}