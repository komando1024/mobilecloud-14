/*
 * 
 * Copyright 2014 Jules White
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.magnum.mobilecloud.video.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.magnum.mobilecloud.video.model.Video;

public class VideoFileManager {

	private Path targetDir_ = Paths.get("videos");

	public static VideoFileManager get() throws IOException {
		return new VideoFileManager();
	}
		
	private VideoFileManager() throws IOException{
		if(!Files.exists(targetDir_)){
			Files.createDirectories(targetDir_);
		}
	}
	
	private Path getVideoPath(Video v){
		assert(v != null);		
		return targetDir_.resolve("video"+v.getId()+".mpg");
	}
	
	public boolean hasVideoData(Video v){
		Path source = getVideoPath(v);
		return Files.exists(source);
	}
	
	public void copyVideoData(Video v, OutputStream out) throws IOException {
		Path source = getVideoPath(v);
		if(!Files.exists(source)){
			throw new FileNotFoundException("Unable to find the referenced video file for videoId:"+v.getId());
		}
		Files.copy(source, out);
	}
	
	public void saveVideoData(Video v, InputStream videoData) throws IOException{
		assert(videoData != null);		
		Path target = getVideoPath(v);
		Files.copy(videoData, target, StandardCopyOption.REPLACE_EXISTING);
	}
	
}
