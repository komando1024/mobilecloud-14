package org.magnum.dataup;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.magnum.dataup.model.Video;
import org.magnum.dataup.service.VideoServiceImpl;
import org.magnum.dataup.service.VideoServiceInterface;


public class VideoSvcImplTest{

	private VideoServiceInterface videoSvc = new VideoServiceImpl();
	
	@Test
	public void getVideoList() {
		//add videos
		videoSvc.addVideo(TestData.randomVideo());
		videoSvc.addVideo(TestData.randomVideo());
		videoSvc.addVideo(TestData.randomVideo());
		
		//get video list
		Collection<Video> list = videoSvc.getVideoList();
		Assert.assertNotNull(list);
		Assert.assertEquals(3, list.size());
	}
}