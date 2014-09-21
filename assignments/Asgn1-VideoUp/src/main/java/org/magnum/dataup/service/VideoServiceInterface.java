package org.magnum.dataup.service;

import java.util.Collection;

import org.magnum.dataup.model.Video;

public interface VideoServiceInterface {

	public Collection<Video> getVideoList();

	public Video addVideo(Video video);

	public Video findVideoById(long id);

}