package com.OSexample.osapplication;

import java.util.List;


/**
 * YouTubeの情報をオブジェクトで定義するためのクラス
 * 
 */
public class OSYoutubeVO {

	public OSYoutubeVO(){		
	}
	public OSYoutubeVO(List<?> list){
		setAllData(list);
	}
	
	private void setAllData(List<?> list) {
		setYoutubeURL(getYoutubeURL());		
	}

	//アドレス
	private String youtubeURL;
	
	
	public String getYoutubeURL() {
		return youtubeURL;
	}

	public void setYoutubeURL(String youtubeURL) {
		this.youtubeURL = youtubeURL;
	}
	
	
	
	
}
