package com.OSexample.osapplication;

import java.util.List;


/**
 * YouTubeの情報をオブジェクトで定義するためのクラス
 * 
 */
public class OSYoutubeVO {

	public OSYoutubeVO(){		
	}
	public OSYoutubeVO(String url, String videoID){
		setAllData(url,videoID);
	}
	
	private void setAllData(String url, String videoID) {
		setYoutubeURL(url);	
		setVideoID(videoID);
	}

	//アドレス
	private String youtubeURL;
	//Video ID
	private String videoID;
	
	
	public String getYoutubeURL() {
		return youtubeURL;
	}
	public void setYoutubeURL(String youtubeURL) {
		this.youtubeURL = youtubeURL;
	}
	public String getVideoID() {
		return videoID;
	}
	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}
	
	
	
	
}
